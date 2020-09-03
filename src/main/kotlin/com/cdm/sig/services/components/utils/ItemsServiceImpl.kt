package com.cdm.sig.services.components.utils

import com.cdm.sig.models.integrations.Item
import com.cdm.sig.repositories.utils.ItemsRepository
import com.cdm.sig.services.apis.utils.ItemsServiceAPI
import com.cdm.sig.utils.archive.GenericServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class ItemsServiceImpl : GenericServiceImpl<Item, Long>(), ItemsServiceAPI {

    @Autowired
    var repository: ItemsRepository? = null

    override fun getRepository(): JpaRepository<Item, Long> {
        return repository!!
    }
}