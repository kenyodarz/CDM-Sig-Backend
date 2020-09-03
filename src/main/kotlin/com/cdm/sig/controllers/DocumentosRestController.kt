package com.cdm.sig.controllers

import com.cdm.sig.models.Documento
import com.cdm.sig.services.apis.DocumentoServiceAPI
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
@CrossOrigin(origins = ["*"], maxAge = 3600, methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT])
@RequestMapping("/api/documentos")
class DocumentosRestController {

    @Autowired
    var serviceAPI: DocumentoServiceAPI? = null
    @Autowired
    var empleadoServiceAPI: EmpleadoServiceAPI? = null


    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun getAll(): List<Documento>{
        return serviceAPI!!.getAll()
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun findById(@PathVariable id: Long): Documento {
        return serviceAPI!!.getT(id)
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun save(@Valid @RequestBody entity : Documento, result: BindingResult): ResponseEntity<Any> {
        if(result.hasErrors()) return this.validar(result)
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceAPI!!.save(entity))
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun delete(@PathVariable id: Long) : ResponseEntity<Any>{
        val entity: Documento? = serviceAPI!!.getT(id)
        if(entity != null){
            serviceAPI!!.delete(id)
        }else {
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        return ResponseEntity(entity, HttpStatus.OK)
    }


    open fun validar(result: BindingResult):ResponseEntity<Any>{
        val errores: MutableMap<String, Any> = HashMap()
        result.fieldErrors.forEach { err ->
            run {
                errores.put(err.field, " El campo " + err.field + " " + err.defaultMessage)
            }
        }
        return ResponseEntity.badRequest().body(errores)
    }

    @PostMapping("/save-file/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun saveWithPhoto(@Valid entity: Documento,result: BindingResult ,
                      @RequestParam archivo: MultipartFile, @PathVariable id: String): ResponseEntity<Documento> {
        if (!archivo.isEmpty) {
            entity.archivo = archivo.bytes
            entity.empleado = empleadoServiceAPI!!.getT(id)
        }
        val obj = serviceAPI!!.save(entity)
        return ResponseEntity(obj, HttpStatus.CREATED)
    }

    @GetMapping("/image/{id}")
    fun viewPhoto(@PathVariable id: Long): ResponseEntity<Any> {

        val entity: Documento? = serviceAPI!!.getT(id)



        if (entity?.archivo == null) return ResponseEntity.notFound().build()

        val imagen: Resource = ByteArrayResource(entity.archivo!!)

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen)
    }
    @GetMapping("/pdf/{id}")
    fun viewPDF(@PathVariable id: Long): ResponseEntity<Any> {

        val entity: Documento? = serviceAPI!!.getT(id)

        if (entity?.archivo == null) return ResponseEntity.notFound().build()

        val pdf: Resource = ByteArrayResource(entity.archivo!!)

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(pdf)
    }

    @GetMapping("/empleado/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun findDocumentoByEmpleado(@PathVariable id: String): ResponseEntity<Any>{
        val documentos: List<Documento> = serviceAPI!!.findDocumentoByEmpleado(id)
        return ResponseEntity.ok(documentos)
    }

}