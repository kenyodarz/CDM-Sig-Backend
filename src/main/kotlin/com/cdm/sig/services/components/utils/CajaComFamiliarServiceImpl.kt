package com.cdm.sig.services.components.utils

import com.cdm.sig.models.integrations.CajaComFamiliar
import com.cdm.sig.repositories.utils.CajaComFamiliarRepository
import com.cdm.sig.services.apis.utils.CajaComFamiliarServiceAPI
import com.cdm.sig.utils.archive.GenericServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class CajaComFamiliarServiceImpl : GenericServiceImpl<CajaComFamiliar, String>(), CajaComFamiliarServiceAPI {

    @Autowired
    val repository: CajaComFamiliarRepository? = null

    override fun getRepository(): JpaRepository<CajaComFamiliar, String> {
        return repository!!
    }
}