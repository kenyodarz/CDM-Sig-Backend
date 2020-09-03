package com.cdm.sig.controllers

import com.cdm.sig.models.Capacitacion
import com.cdm.sig.models.Empleado
import com.cdm.sig.services.apis.CapacitacionServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api/capacitaciones")
class CapacitacionRestController {

    @Autowired
    var serviceAPI: CapacitacionServiceAPI? = null

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun getAll(): List<Capacitacion>{
        return serviceAPI!!.getAll()
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun find(@PathVariable id: Long): Capacitacion{
        return serviceAPI!!.getT(id)
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun save(@Valid @RequestBody entity: Capacitacion, result: BindingResult): ResponseEntity<Any> {
        if(result.hasErrors()) return this.validar(result)
        return ResponseEntity.status(HttpStatus.OK).body(serviceAPI!!.save(entity))
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun delete(@PathVariable id: Long) : ResponseEntity<Capacitacion>{
        val entity: Capacitacion? = serviceAPI!!.getT(id)
        if(entity != null){
            serviceAPI!!.delete(id)
        }else {
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        return ResponseEntity(entity, HttpStatus.OK)
    }

    open fun validar(result: BindingResult): ResponseEntity<Any>{
        val errores: MutableMap<String, Any> = HashMap()
        result.fieldErrors.forEach { err ->
            run {
                errores.put(err.field, " El Campo " + err.field + " "+ err.defaultMessage)
            }
        }
        return ResponseEntity.badRequest().body(errores)
    }

    @PutMapping("/{id}/asignar-empleados")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun asignarEmpleados(@RequestBody empleados: List<Empleado>, @PathVariable id: Long): ResponseEntity<Any>{
        val capacitaciones: Capacitacion = serviceAPI?.getT(id) ?: return ResponseEntity.notFound().build()
        empleados.forEach(capacitaciones::addEmpleado)
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.serviceAPI!!.save(capacitaciones))
    }

    @PutMapping("/{id}/eliminar-empleados")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun eliminarEmpleados(@RequestBody empleado: Empleado, @PathVariable id: Long): ResponseEntity<Any>{
        val capacitaciones: Capacitacion = serviceAPI?.getT(id) ?: return ResponseEntity.notFound().build()
        capacitaciones.removeEmpleado(empleado)
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.serviceAPI!!.save(capacitaciones))
    }

    @GetMapping("/empleado/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun buscarEmpleadoPorID(@PathVariable id: String): ResponseEntity<Any>{
        val capacitaciones: List<Capacitacion> = serviceAPI!!.findCapacitacionesByEmpleados(id)
        return ResponseEntity.ok(capacitaciones)
    }

}