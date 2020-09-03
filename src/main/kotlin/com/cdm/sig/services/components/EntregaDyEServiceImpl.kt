package com.cdm.sig.services.components

import com.cdm.sig.models.EntregaDyE
import com.cdm.sig.repositories.EntregaDyERepository
import com.cdm.sig.services.apis.EntregaDyEServiceAPI
import com.cdm.sig.utils.archive.GenericServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class EntregaDyEServiceImpl : GenericServiceImpl<EntregaDyE, Long>(), EntregaDyEServiceAPI {

    @Autowired
    var repository: EntregaDyERepository? = null

    override fun getRepository(): JpaRepository<EntregaDyE, Long> {
        return repository!!
    }

    override fun findEntregaDyEByEmpleado(id: String): List<EntregaDyE> {
        return repository!!.findEntregaDyEByEmpleado(id)
    }
}