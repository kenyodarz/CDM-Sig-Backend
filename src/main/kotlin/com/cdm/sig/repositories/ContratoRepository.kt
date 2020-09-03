package com.cdm.sig.repositories

import com.cdm.sig.models.Contrato
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContratoRepository: JpaRepository<Contrato, Long> {
}