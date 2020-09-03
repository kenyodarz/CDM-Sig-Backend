package com.cdm.sig.services.components

import com.cdm.sig.models.Foto
import com.cdm.sig.repositories.FotoRepository
import com.cdm.sig.services.apis.FotoServiceAPI
import com.cdm.sig.utils.archive.GenericServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class FotoServiceImpl : GenericServiceImpl<Foto, String>(), FotoServiceAPI {

    @Autowired
    var repository: FotoRepository? = null

    override fun getRepository(): JpaRepository<Foto, String> {
        return repository!!
    }
}