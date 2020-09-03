package com.cdm.sig.services.components

import com.cdm.sig.models.Recommendation
import com.cdm.sig.repositories.RecommendationRepository
import com.cdm.sig.services.apis.RecommendationServiceAPI
import com.cdm.sig.utils.archive.GenericServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class RecommendationServiceImpl : GenericServiceImpl<Recommendation, Long>(), RecommendationServiceAPI {

    @Autowired
    var repository: RecommendationRepository? = null

    override fun getRepository(): JpaRepository<Recommendation, Long> {
        return repository!!
    }
}