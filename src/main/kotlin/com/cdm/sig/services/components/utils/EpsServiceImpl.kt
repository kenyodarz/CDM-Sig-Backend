package com.cdm.sig.services.components.utils

import com.cdm.sig.models.integrations.Eps
import com.cdm.sig.repositories.utils.EpsRepository
import com.cdm.sig.services.apis.utils.EpsServiceAPI
import com.cdm.sig.utils.archive.GenericServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class EpsServiceImpl : GenericServiceImpl<Eps, String>(), EpsServiceAPI {

    @Autowired
    var repository: EpsRepository? = null

    override fun getRepository(): JpaRepository<Eps, String> {
        return repository!!;
    }
}