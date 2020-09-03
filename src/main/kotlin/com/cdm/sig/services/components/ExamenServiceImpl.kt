package com.cdm.sig.services.components

import com.cdm.sig.models.Examen
import com.cdm.sig.repositories.ExamenRepository
import com.cdm.sig.services.apis.ExamenServiceAPI
import com.cdm.sig.utils.archive.GenericServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.validation.constraints.NotNull

@Service
class ExamenServiceImpl : GenericServiceImpl<Examen, Long>(), ExamenServiceAPI {

    @Autowired
    var repository: ExamenRepository? = null

    override fun getRepository(): JpaRepository<Examen, Long> {
        return repository!!
    }

    @NotNull
    @Transactional
    override fun findExamenByEmpleado(cedula: String): List<Examen> {
        return repository!!.findExamenByEmpleado(cedula)
    }
}