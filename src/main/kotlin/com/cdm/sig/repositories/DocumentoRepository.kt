package com.cdm.sig.repositories

import com.cdm.sig.models.Documento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface DocumentoRepository: JpaRepository<Documento, Long> {

    @Query("select d from Documento d join fetch d.empleado e where e.cedula = ?1")
    fun findDocumentoByEmpleado(cedula: String): List<Documento>
}