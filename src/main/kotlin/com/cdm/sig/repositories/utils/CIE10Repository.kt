package com.cdm.sig.repositories.utils

import com.cdm.sig.models.integrations.CIE10
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CIE10Repository: JpaRepository<CIE10, String> {
}