package com.cdm.sig.controllers

import com.cdm.sig.models.Contrato
import com.cdm.sig.services.apis.ContratoServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api/contratos")
class ContratoRestController {

    @Autowired
    var serviceAPI: ContratoServiceAPI? = null

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun getAll(): List<Contrato>{
        return serviceAPI!!.getAll()
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun find(@PathVariable id: Long): Contrato{
        return serviceAPI!!.getT(id)
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun save(@Valid @RequestBody entity: Contrato, result: BindingResult): ResponseEntity<Any> {
        if(result.hasErrors()) return this.validar(result)
        return ResponseEntity.status(HttpStatus.OK).body(serviceAPI!!.save(entity))
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun delete(@PathVariable id: Long) : ResponseEntity<Contrato>{
        val entity: Contrato? = serviceAPI!!.getT(id)
        if(entity != null){
            serviceAPI!!.delete(id)
        }else {
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        return ResponseEntity(entity, HttpStatus.OK)
    }

    open fun validar(result: BindingResult): ResponseEntity<Any>{
        val errores: kotlin.collections.MutableMap<String, Any> = HashMap()

        result.fieldErrors.forEach { err ->
            run {
                errores.put(err.field, " El Campo " + err.field + " "+ err.defaultMessage)
            }
        }

        return ResponseEntity.badRequest().body(errores)
    }

}