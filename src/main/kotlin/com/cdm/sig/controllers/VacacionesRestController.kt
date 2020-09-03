package com.cdm.sig.controllers

import com.cdm.sig.models.Vacaciones
import com.cdm.sig.services.apis.VacacionesServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@CrossOrigin(origins = ["*"], maxAge = 1L)
@RequestMapping("/api/vacaciones")
class VacacionesRestController {

    @Autowired
    var serviceAPI: VacacionesServiceAPI? = null

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun getAll(): List<Vacaciones> {
        return serviceAPI!!.getAll()
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun find(@PathVariable id: Long): Vacaciones {
        return serviceAPI!!.getT(id)
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun save(@Valid @RequestBody entity: Vacaciones, result: BindingResult): ResponseEntity<Any> {
        if (result.hasErrors()) return this.validar(result)
        return ResponseEntity.status(HttpStatus.OK).body(serviceAPI!!.save(entity))
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun delete(@PathVariable id: Long): ResponseEntity<Vacaciones> {
        val entity: Vacaciones? = serviceAPI!!.getT(id)
        if (entity != null) {
            serviceAPI!!.delete(id)
        } else {
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        return ResponseEntity(entity, HttpStatus.OK)
    }

    open fun validar(result: BindingResult): ResponseEntity<Any> {
        val errores: MutableMap<String, Any> = HashMap()

        result.fieldErrors.forEach { err ->
            run {
                errores.put(err.field, " El Campo " + err.field + " " + err.defaultMessage)
            }
        }

        return ResponseEntity.badRequest().body(errores)
    }

    @GetMapping("/empleado/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun findVacacionesByEmpleado(@PathVariable id: String): ResponseEntity<Any> {
        val returnList: List<Vacaciones> = serviceAPI!!.findVacacionesByEmpleado(id)
        return ResponseEntity.ok(returnList)
    }

    @GetMapping("/empleado/{id}/tomadas/{idContrato}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun findVacacionesTomadasByEmpleados(@PathVariable id: String, @PathVariable idContrato: Long): ResponseEntity<Any> {
        val iterableVacaciones: Iterable<Long> = serviceAPI!!.findVacacionesTomadasByEmpleados(id, idContrato)
        return ResponseEntity.ok(iterableVacaciones)
    }

}