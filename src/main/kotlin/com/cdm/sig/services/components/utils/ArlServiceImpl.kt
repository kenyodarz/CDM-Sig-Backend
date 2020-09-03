package com.cdm.sig.services.components.utils

import com.cdm.sig.models.integrations.Arl
import com.cdm.sig.repositories.utils.ArlRepository
import com.cdm.sig.services.apis.utils.ArlServiceAPI
import com.cdm.sig.utils.archive.GenericServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class ArlServiceImpl : GenericServiceImpl<Arl, String>(), ArlServiceAPI {

    @Autowired
    var repository: ArlRepository? = null

    override fun getRepository(): JpaRepository<Arl, String> {
        return repository!!
    }
}