package com.cdm.sig.repositories

import com.cdm.sig.models.Foto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FotoRepository: JpaRepository<Foto,String>{}