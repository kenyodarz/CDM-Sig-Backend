package com.cdm.sig.services.components

import com.cdm.sig.models.Incapacidad
import com.cdm.sig.repositories.IncapacidadRepository
import com.cdm.sig.services.apis.IncapacidadServiceAPI
import com.cdm.sig.utils.archive.GenericServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional
import javax.validation.constraints.NotNull

@Service
class IncapacidadServiceImpl: GenericServiceImpl<Incapacidad, Long>(), IncapacidadServiceAPI {

    @Autowired
    var repository: IncapacidadRepository? = null

    override fun getRepository(): JpaRepository<Incapacidad, Long> {
        return repository!!
    }

    @NotNull
    @Transactional
    override fun findIncapacidadByEmpleado(cedula: String): List<Incapacidad> {
        return repository!!.findIncapacidadByEmpleado(cedula)
    }
}