package com.cdm.sig.models

import com.cdm.sig.models.integrations.CIE10
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name = "incapacidades")
class Incapacidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idIncapacidad: Long? = null

    @Column
    @NotNull
    var fechaInicio: LocalDate? = null

    @Column
    @NotNull
    var fechaFin: LocalDate? = null

    @Column
    @NotBlank
    @NotEmpty
    var entidad: String? = null

    @Column
    @NotBlank
    @NotEmpty
    var enfermedad: String? = null

    @OneToOne(fetch = FetchType.LAZY)
    var cie10: CIE10? = null

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    var empleado: Empleado? = null

    @Column
    @NotBlank
    @NotEmpty
    var estado: String? = null
}
