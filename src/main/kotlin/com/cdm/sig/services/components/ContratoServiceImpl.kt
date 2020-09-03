package com.cdm.sig.services.components

import com.cdm.sig.models.Contrato
import com.cdm.sig.repositories.ContratoRepository
import com.cdm.sig.services.apis.ContratoServiceAPI
import com.cdm.sig.utils.archive.GenericServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class ContratoServiceImpl: GenericServiceImpl<Contrato, Long>(), ContratoServiceAPI {

    @Autowired
    val serviceAPI: ContratoRepository? = null

    override fun getRepository(): JpaRepository<Contrato, Long> {
        return serviceAPI!!
    }
}