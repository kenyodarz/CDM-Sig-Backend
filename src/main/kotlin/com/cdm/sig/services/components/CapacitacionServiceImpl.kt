package com.cdm.sig.services.components


import com.cdm.sig.models.Capacitacion
import com.cdm.sig.repositories.CapacitacionRepository
import com.cdm.sig.services.apis.CapacitacionServiceAPI
import com.cdm.sig.utils.archive.GenericServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional
import javax.validation.constraints.NotNull

@Service
class CapacitacionServiceImpl: GenericServiceImpl<Capacitacion, Long>(), CapacitacionServiceAPI {

    @Autowired
    val repository: CapacitacionRepository? = null

    override fun getRepository(): JpaRepository<Capacitacion, Long> {
        return repository!!
    }

    @NotNull
    @Transactional
    override fun findCapacitacionesByEmpleados(cedula: String): List<Capacitacion> {
        return repository!!.findCapacitacionByEmpleados(cedula)
    }
}