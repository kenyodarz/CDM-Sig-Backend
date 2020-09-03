package com.cdm.sig.services.apis

import com.cdm.sig.models.Incapacidad
import com.cdm.sig.utils.archive.GenericServiceAPI

interface IncapacidadServiceAPI: GenericServiceAPI<Incapacidad, Long> {
    fun findIncapacidadByEmpleado(cedula: String): List<Incapacidad>
}