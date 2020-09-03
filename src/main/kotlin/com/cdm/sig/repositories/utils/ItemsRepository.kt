package com.cdm.sig.repositories.utils

import com.cdm.sig.models.integrations.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemsRepository : JpaRepository<Item, Long> {
}