package com.cdm.sig.services.apis

import com.cdm.sig.models.Vacaciones
import com.cdm.sig.utils.archive.GenericServiceAPI

interface VacacionesServiceAPI : GenericServiceAPI<Vacaciones, Long> {
    fun findVacacionesByEmpleado(cedula: String): List<Vacaciones>
    fun findVacacionesTomadasByEmpleados(cedula: String, idContrato: Long): Iterable<Long>
}