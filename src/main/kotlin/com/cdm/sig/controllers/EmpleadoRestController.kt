package com.cdm.sig.controllers

import com.cdm.sig.models.Empleado
import com.cdm.sig.services.apis.EmpleadoServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600, methods = [RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT])
@RequestMapping("/api/empleados")
class EmpleadoRestController {

    @Autowired
    var serviceAPI: EmpleadoServiceAPI? = null

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun getAll(): List<Empleado>{
        return serviceAPI!!.getAll()
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun findById(@PathVariable id: String): Empleado {
        return serviceAPI!!.getT(id)
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun save(@Valid @RequestBody entity : Empleado, result: BindingResult): ResponseEntity<Any> {
        if(result.hasErrors()) return this.validar(result)
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceAPI!!.save(entity))
    }

    @PostMapping("/save-with-photo")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun saveWithPhoto(@Valid entity: Empleado,result: BindingResult ,
                      @RequestParam archivo: MultipartFile): ResponseEntity<Empleado> {
        if (!archivo.isEmpty) {
            entity.foto = archivo.bytes
        }
        val obj = serviceAPI!!.save(entity)
        return ResponseEntity(obj, HttpStatus.OK)
    }

    @GetMapping("/uploads/img/{id}")
    fun viewPhoto(@PathVariable id: String): ResponseEntity<Any> {

        val entity: Empleado? = serviceAPI!!.getT(id)

        if (entity?.foto == null) return ResponseEntity.notFound().build()

        val imagen: Resource = ByteArrayResource(entity.foto!!)

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen)

    }

    @PutMapping("/edit-with-photo/{id}")
    fun editWithPhoto(@Valid entity: Empleado,result: BindingResult ,
                      @RequestParam archivo: MultipartFile,
                      @PathVariable id: String  ): ResponseEntity<Any>{
        val optEmpleado: Empleado = serviceAPI?.getT(id) ?: return ResponseEntity.notFound().build()

        optEmpleado.cedula = entity.cedula
        optEmpleado.nombres = entity.nombres
        optEmpleado.apellidos = entity.apellidos
        optEmpleado.direccion = entity.direccion
        optEmpleado.telefono = entity.telefono
        optEmpleado.eps = entity.eps
        optEmpleado.afp = entity.afp
        optEmpleado.arl = entity.arl
        optEmpleado.fechaNacimiento = entity.fechaNacimiento
        optEmpleado.enCasoEmergencia = entity.enCasoEmergencia
        optEmpleado.cajaComFamiliar = entity.cajaComFamiliar
        optEmpleado.alergia = entity.alergia
        optEmpleado.medimentos = entity.medimentos

        if (!archivo.isEmpty) {
            optEmpleado.foto = archivo.bytes
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceAPI!!.save(optEmpleado))
    }


    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun delete(@PathVariable id: String) : ResponseEntity<Any>{
        val entity: Empleado? = serviceAPI!!.getT(id)
        if(entity != null){
            serviceAPI!!.delete(id)
        }else {
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        return ResponseEntity(entity, HttpStatus.OK)
    }


    open fun validar(result: BindingResult):ResponseEntity<Any>{
        val errores: kotlin.collections.MutableMap<String, Any> = HashMap()
        result.fieldErrors.forEach { err ->
            run {
                errores.put(err.field, " El campo " + err.field + " " + err.defaultMessage)
            }
        }
        return ResponseEntity.badRequest().body(errores)
    }
}