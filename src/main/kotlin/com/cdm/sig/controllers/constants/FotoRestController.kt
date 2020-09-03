package com.cdm.sig.controllers.constants

import com.cdm.sig.models.Foto
import com.cdm.sig.services.apis.FotoServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600, methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT])
@RequestMapping("/api/fotos/")
class FotoRestController {
    @Autowired
    var serviceAPI: FotoServiceAPI? = null

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun getAll(): List<Foto> {
        return serviceAPI!!.getAll()
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun findById(@PathVariable id: String): Foto {
        return serviceAPI!!.getT(id)
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun saveWithPhoto(entity: Foto, @RequestParam archivo: MultipartFile): ResponseEntity<Foto> {
        if (!archivo.isEmpty) {
            entity.foto = archivo.bytes
        }
        val obj = serviceAPI!!.save(entity)
        return ResponseEntity(obj, HttpStatus.OK)
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun delete(@PathVariable id: String): ResponseEntity<Any> {
        val entity: Foto? = serviceAPI!!.getT(id)
        if (entity != null) {
            serviceAPI!!.delete(id)
        } else {
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        return ResponseEntity(entity, HttpStatus.OK)
    }

    @GetMapping("/image/{id}")
    fun viewPhoto(@PathVariable id: String): ResponseEntity<Any> {

        val entity: Foto? = serviceAPI!!.getT(id)

        if (entity?.foto == null) {

            val defaultPhoto = serviceAPI!!.getT("0")
            val imagen: Resource = ByteArrayResource(defaultPhoto.foto!!)

            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen)
        }

        val imagen: Resource = ByteArrayResource(entity.foto!!)

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen)
    }
}