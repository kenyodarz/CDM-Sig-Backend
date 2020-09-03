package com.cdm.sig.services.apis

import com.cdm.sig.models.EntregaDyE
import com.cdm.sig.utils.archive.GenericServiceAPI

interface EntregaDyEServiceAPI : GenericServiceAPI<EntregaDyE, Long> {
    fun findEntregaDyEByEmpleado(id: String): List<EntregaDyE>
}