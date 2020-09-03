package com.cdm.sig.services.apis

import com.cdm.sig.models.Examen
import com.cdm.sig.utils.archive.GenericServiceAPI

interface ExamenServiceAPI: GenericServiceAPI<Examen, Long>{
    fun findExamenByEmpleado(cedula: String): List<Examen>
}