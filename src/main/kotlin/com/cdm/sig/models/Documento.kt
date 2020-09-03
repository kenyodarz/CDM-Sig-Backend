package com.cdm.sig.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull


@Entity
@Table(name = "documentos")
class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idDocumento: Long? = null

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    var empleado: Empleado? = null

    @Column
    @NotEmpty
    @NotBlank
    var tipo: String? = null

    @Column
    @NotEmpty
    @NotBlank
    var nombre: String? = null

    @Column
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    var createAt: Date? = null

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @JsonIgnore
    var archivo: ByteArray? = null

    @PrePersist
    fun CreatedDate() {
        createAt = Date()
    }

    fun getArchivoHashCode(): Int? {
        return if (archivo != null) {
            this.archivo.hashCode()
        } else null
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other !is Documento) {
            return false
        }
        return idDocumento != null && idDocumento == other.idDocumento
    }
}