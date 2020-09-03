package com.cdm.sig.repositories.utils

import com.cdm.sig.models.integrations.CajaComFamiliar
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CajaComFamiliarRepository : JpaRepository<CajaComFamiliar, String>

