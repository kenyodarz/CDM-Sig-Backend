package com.cdm.sig.models

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "vacaciones")
class Vacaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idVacaciones: Long? = null

    @Column
    @NotNull
    var fechaInicio: LocalDate? = null

    @Column
    @NotNull
    var fechaFin: LocalDate? = null

    @OneToOne(fetch = FetchType.LAZY)
    var contrato: Contrato? = null


}