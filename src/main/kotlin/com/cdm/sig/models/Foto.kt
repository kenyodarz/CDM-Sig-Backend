package com.cdm.sig.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "fotos")
class Foto {
    @Id
    var idFoto: String?=null

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @JsonIgnore
    var foto: ByteArray? = null

    fun getArchivoHashCode(): Int? {
        return if (foto != null) {
            this.foto.hashCode()
        } else null
    }

}