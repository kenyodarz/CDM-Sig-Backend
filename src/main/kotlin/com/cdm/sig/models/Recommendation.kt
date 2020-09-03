package com.cdm.sig.models

import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "recomendaciones")
class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idRecomendaciones: Long? = null

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    var examen: Examen? = null

    @NotBlank
    @Column
    var recommendation: String? = null

    @NotBlank
    @Column
    var tipoSeguimiento: String? = null

    @Column
    var primeraSeguimiento: String? = null

    @Column
    var segundaSeguimiento: String? = null

    @Column
    var terceraSeguimiento: String? = null

    @CreationTimestamp
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var createAt: Date? = null

    @PrePersist
    fun FechaCreacion() {
        createAt = Date()
    }

}