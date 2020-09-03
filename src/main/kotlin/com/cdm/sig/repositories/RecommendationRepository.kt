package com.cdm.sig.repositories

import com.cdm.sig.models.Recommendation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RecommendationRepository : JpaRepository<Recommendation, Long> {
}