package com.cdm.sig.models

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "examenes")
class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idExamen: Long? = null

    @NotNull
    @Column
    var fecha: LocalDate? = null

    @NotNull
    @Column
    var concepto: Boolean? = null

    @Column(columnDefinition = "varchar(255) default 'Sin Restricción'")
    var restriccion: String? = null

    @Column
    var tipoExamen: String? = null

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    var contrato: Contrato? = null


}