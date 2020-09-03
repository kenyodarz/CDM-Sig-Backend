package com.cdm.sig.utils.archive

import org.springframework.data.jpa.repository.JpaRepository
import java.io.Serializable

interface GenericServiceAPI<T, ID : Serializable> {

    fun getAll(): List<T>

    fun getT(id: ID): T

    fun save(entity: T): T

    fun delete(id: ID)

    fun getRepository(): JpaRepository<T, ID>
}