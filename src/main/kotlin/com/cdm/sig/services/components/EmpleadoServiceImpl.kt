package com.cdm.sig.services.components

import com.cdm.sig.models.Empleado
import com.cdm.sig.repositories.EmpleadoRepository
import com.cdm.sig.services.apis.EmpleadoServiceAPI
import com.cdm.sig.utils.archive.GenericServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class EmpleadoServiceImpl: GenericServiceImpl<Empleado, String>(), EmpleadoServiceAPI {

    @Autowired
    val repository: EmpleadoRepository? = null


    override fun getRepository(): JpaRepository<Empleado, String> {
        return repository!!
    }

}