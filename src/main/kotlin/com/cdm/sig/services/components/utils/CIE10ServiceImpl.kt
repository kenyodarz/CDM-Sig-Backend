package com.cdm.sig.services.components.utils

import com.cdm.sig.models.integrations.CIE10
import com.cdm.sig.repositories.utils.CIE10Repository
import com.cdm.sig.services.apis.utils.CIE10ServiceAPI
import com.cdm.sig.utils.archive.GenericServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class CIE10ServiceImpl : GenericServiceImpl<CIE10, String>(), CIE10ServiceAPI {

    @Autowired
    var repository: CIE10Repository?=null

    override fun getRepository(): JpaRepository<CIE10, String> {
        return repository!!
    }
}