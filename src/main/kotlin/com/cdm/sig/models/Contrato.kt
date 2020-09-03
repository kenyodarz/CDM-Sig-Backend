package com.cdm.sig.models

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "contratos")
class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idContrato: Long? = null

    @Column
    var tipoContrato: String? = null

    @Column
    var fechaInicio: LocalDate? = null

    @Column
    var fechaFin: LocalDate? = null

    @Column
    var salario: Double? = null

    @OneToOne(fetch = FetchType.LAZY)
    var empleado: Empleado? = null

    @Column
    var liquidado: Boolean? = null

}