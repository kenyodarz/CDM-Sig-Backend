package com.cdm.sig.repositories

import com.cdm.sig.models.Incapacidad
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface IncapacidadRepository: JpaRepository<Incapacidad, Long> {

    @Query("select i from Incapacidad i join fetch i.empleado e where e.cedula = ?1")
    fun findIncapacidadByEmpleado(cedula: String): List<Incapacidad>

}