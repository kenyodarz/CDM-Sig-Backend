package com.cdm.sig.models.integrations

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table (name = "caja_com_familiar")
class CajaComFamiliar {
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