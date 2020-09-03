package com.cdm.sig.services.components

import com.cdm.sig.models.Vacaciones
import com.cdm.sig.repositories.VacacionesRepository
import com.cdm.sig.services.apis.VacacionesServiceAPI
import com.cdm.sig.utils.archive.GenericServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional
import javax.validation.constraints.NotNull

@Service
class VacacionesServicesImpl : GenericServiceImpl<Vacaciones, Long>(), VacacionesServiceAPI {

    @Autowired
    var repository: VacacionesRepository? = null

    override fun getRepository(): JpaRepository<Vacaciones, Long> {
        return repository!!
    }

    @NotNull
    @Transactional
    override fun findVacacionesByEmpleado(cedula: String): List<Vacaciones> {
        return repository!!.findVacacionesByEmpleado(cedula)
    }

    @NotNull
    @Transactional
    override fun findVacacionesTomadasByEmpleados(cedula: String, idContrato: Long): Iterable<Long> {
        return repository!!.findVacacionesTomadasByEmpleados(cedula, idContrato)
    }
}