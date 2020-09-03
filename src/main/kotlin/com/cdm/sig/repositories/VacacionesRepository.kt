package com.cdm.sig.repositories

import com.cdm.sig.models.Vacaciones
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface VacacionesRepository : JpaRepository<Vacaciones, Long> {

    @Query("select v from Vacaciones v join v.contrato c where c.empleado.cedula=?1")
    fun findVacacionesByEmpleado(cedula: String): List<Vacaciones>

    @Query("select v.idVacaciones from Vacaciones v " +
            "join v.contrato c join c.empleado e where e.cedula=?1 and c.idContrato=?2 group by v.idVacaciones")
    fun findVacacionesTomadasByEmpleados(cedula: String, idContrato: Long): Iterable<Long>
    /**
     * Revisar que ocurre cuando hay mas de un contrato por vacaciones
     * y también ver si se puede pasar mas el id del contrato
     */
}