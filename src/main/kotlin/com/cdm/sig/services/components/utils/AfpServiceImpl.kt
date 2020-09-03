package com.cdm.sig.services.components.utils

import com.cdm.sig.models.integrations.Afp
import com.cdm.sig.repositories.utils.AfpRepository
import com.cdm.sig.services.apis.utils.AfpServiceAPI
import com.cdm.sig.utils.archive.GenericServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class AfpServiceImpl : GenericServiceImpl<Afp, String>(), AfpServiceAPI {

    @Autowired
    var repository: AfpRepository? = null

    override fun getRepository(): JpaRepository<Afp, String> {
        return repository!!
    }
}