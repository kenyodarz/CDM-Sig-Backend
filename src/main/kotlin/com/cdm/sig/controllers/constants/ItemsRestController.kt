package com.cdm.sig.controllers.constants

import com.cdm.sig.models.integrations.Item
import com.cdm.sig.services.apis.utils.ItemsServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api/items")
class ItemsRestController {

    @Autowired
    var serviceAPI: ItemsServiceAPI? = null

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun getAll(): List<Item> {
        return serviceAPI!!.getAll()
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun find(@PathVariable id: Long): Item {
        return serviceAPI!!.getT(id)
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun save(@Valid @RequestBody entity: Item, bindingResult: BindingResult): ResponseEntity<Any> {
        return if (bindingResult.hasErrors()) {
            this.validar(bindingResult)
        } else {
            ResponseEntity.status(HttpStatus.OK).body(serviceAPI!!.save(entity))
        }
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun delete(@PathVariable id: Long): ResponseEntity<Item> {
        val entity: Item? = serviceAPI!!.getT(id)
        if (entity != null) {
            serviceAPI!!.delete(id)
        } else {
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        return ResponseEntity(entity, HttpStatus.OK)
    }

    fun validar(bindingResult: BindingResult): ResponseEntity<Any> {
        val errores: MutableMap<String, Any> = HashMap()
        bindingResult.fieldErrors.forEach { err ->
            run {
                errores.put(err.field, " El campo " + err.field + " " + err.defaultMessage)
            }
        }
        return ResponseEntity.badRequest().body(errores)
    }
}