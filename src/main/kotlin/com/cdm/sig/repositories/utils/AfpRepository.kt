package com.cdm.sig.repositories.utils

import com.cdm.sig.models.integrations.Afp
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AfpRepository : JpaRepository<Afp, String>