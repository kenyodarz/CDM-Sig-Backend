package com.cdm.sig.repositories.utils

import com.cdm.sig.models.integrations.Arl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArlRepository : JpaRepository<Arl, String> {
}