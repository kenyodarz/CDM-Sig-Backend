package com.cdm.sig.controllers.constants

import com.cdm.sig.models.Empleado
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api/archivos/")
class UploadFileRestController {

    @PostMapping("upload/photos")
    @Throws(IOException::class)
    fun uploadPhoto(@RequestParam("archivo") archivo: MultipartFile?, cedula: String): ResponseEntity<Any> {
        if (archivo == null || archivo.isEmpty) {
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        val builder = StringBuilder()
        //builder.append(System.getProperty("user.home"))
        builder.append("E:")
        builder.append(File.separator)
        builder.append("ARCHIVOS")
        builder.append(File.separator)
        builder.append("SIG")
        builder.append(File.separator)
        builder.append(cedula)
        builder.append(".JPG")
        //builder.append(archivo.originalFilename)
        val fileBytes = archivo.bytes
        val path: Path = Paths.get(builder.toString())
        Files.write(path, fileBytes)
        return ResponseEntity(HttpStatus.CREATED)
    }
    @PostMapping("upload/files")
    @Throws(IOException::class)
    fun uploadFile(@RequestParam("archivo") archivo: MultipartFile?, cedula: String): String? {
        if (archivo == null || archivo.isEmpty) {
            return "Por favor seleccione un archivo"
        }
        val builder = StringBuilder()
        //builder.append(System.getProperty("user.home"))
        builder.append("E:")
        builder.append(File.separator)
        builder.append("ARCHIVOS")
        builder.append(File.separator)
        builder.append("SIG")
        builder.append(File.separator)
        builder.append(cedula)
        builder.append(" - ")
        builder.append(archivo.originalFilename)
        val fileBytes = archivo.bytes
        val path: Path = Paths.get(builder.toString())
        Files.write(path, fileBytes)
        return "Archivo cargado correctamente [$builder]"
    }
}