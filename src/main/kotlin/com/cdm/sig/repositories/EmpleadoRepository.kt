package com.cdm.sig.repositories


import com.cdm.sig.models.Empleado
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface EmpleadoRepository :  JpaRepository <Empleado, String> {
}