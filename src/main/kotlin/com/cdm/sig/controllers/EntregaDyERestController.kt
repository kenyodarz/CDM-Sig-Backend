package com.cdm.sig.controllers

import com.cdm.sig.models.EntregaDyE
import com.cdm.sig.models.integrations.Item
import com.cdm.sig.services.apis.EntregaDyEServiceAPI
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
@RequestMapping("/api/entregas")
class EntregaDyERestController {

    @Autowired
    var serviceAPI: EntregaDyEServiceAPI? = null

    @Autowired
    var itemsServiceAPI: ItemsServiceAPI? = null

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun getAll(): List<EntregaDyE> {
        return serviceAPI!!.getAll()
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun find(@PathVariable id: Long): EntregaDyE {
        return serviceAPI!!.getT(id)
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun save(@Valid @RequestBody entity: EntregaDyE, result: BindingResult): ResponseEntity<Any> {
        if (result.hasErrors()) return this.validar(result)
        return ResponseEntity.status(HttpStatus.OK).body(serviceAPI!!.save(entity))
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun delete(@PathVariable id: Long): ResponseEntity<EntregaDyE> {
        val entity: EntregaDyE? = serviceAPI!!.getT(id)
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

    @PutMapping("/{id}/items/cargar")
    fun cargarItems(@RequestBody items: List<Item>, @PathVariable id: Long): ResponseEntity<Any> {
        val entrega: EntregaDyE = serviceAPI?.getT(id) ?: return ResponseEntity.notFound().build()
        items.forEach(entrega::addItem)
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.serviceAPI!!.save(entrega))
    }

    @PutMapping("/{id}/items/eliminar")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun eliminarItems(@RequestBody item: Item, @PathVariable id: Long): ResponseEntity<Any> {
        val entrega: EntregaDyE = serviceAPI?.getT(id) ?: return ResponseEntity.notFound().build()
        val items: Item = itemsServiceAPI?.getT(item.idItem!!) ?: return ResponseEntity.notFound().build()
        entrega.removeItem(items)
        return ResponseEntity.status(HttpStatus.OK).body(this.serviceAPI!!.save(entrega))
    }

    @GetMapping("/empleado/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun findEntregaDyEByEmpleado(@PathVariable id: String): ResponseEntity<Any> {
        val returnList: List<EntregaDyE> = serviceAPI!!.findEntregaDyEByEmpleado(id)
        return ResponseEntity.ok(returnList)
    }
}