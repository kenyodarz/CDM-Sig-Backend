package com.cdm.sig.services.components

import com.cdm.sig.models.Documento
import com.cdm.sig.repositories.DocumentoRepository
import com.cdm.sig.services.apis.DocumentoServiceAPI
import com.cdm.sig.utils.archive.GenericServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class DocumentoGenericServiceImpl : GenericServiceImpl<Documento, Long>(), DocumentoServiceAPI {

    @Autowired
    var repository: DocumentoRepository? = null

    override fun getRepository(): JpaRepository<Documento, Long> {
        return repository!!
    }

    override fun findDocumentoByEmpleado(cedula: String): List<Documento> {
        return repository!!.findDocumentoByEmpleado(cedula)
    }
}