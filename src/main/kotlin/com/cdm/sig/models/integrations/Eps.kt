package com.cdm.sig.models.integrations

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table
class Eps {
    @Id
    var nit: String? = null

    @Column
    @NotBlank
    var nombre: String? = null
    @Column
    @NotBlank
    var direccion: String? = null
    @Column
    @NotBlank
    var telefono: String? = null
}