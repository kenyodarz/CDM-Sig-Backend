package com.cdm.sig.repositories

import com.cdm.sig.models.Capacitacion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CapacitacionRepository : JpaRepository<Capacitacion, Long> {

    @Query("select c from Capacitacion c join fetch c.empleados a where  a.cedula = ?1")
    fun findCapacitacionByEmpleados(cedula: String): List<Capacitacion>


}