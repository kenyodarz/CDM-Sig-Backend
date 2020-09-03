package com.cdm.sig.services.apis

import com.cdm.sig.models.Capacitacion
import com.cdm.sig.utils.archive.GenericServiceAPI


interface CapacitacionServiceAPI: GenericServiceAPI<Capacitacion, Long> {

    fun findCapacitacionesByEmpleados(cedula: String): List<Capacitacion>

}