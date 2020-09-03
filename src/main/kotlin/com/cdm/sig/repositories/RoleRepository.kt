package com.cdm.sig.repositories

import com.cdm.sig.models.integrations.ERole
import com.cdm.sig.models.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository : JpaRepository<Role, Long>{
    fun findByName(name: ERole): Optional<Role>
}