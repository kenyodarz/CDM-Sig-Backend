package com.cdm.sig.models.integrations

import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "items")
class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idItem: Long? = null

    @Column
    @NotEmpty(message = "El Nombre es obligatorio")
    @NotNull
    @Size(min = 2, max = 50)
    var nombre: String? = null

    @Column
    @NotEmpty(message = "La Referencia es obligatoria")
    @NotNull
    @Size(min = 2, max = 50)
    var referencia: String? = null

    @Column
    @NotEmpty(message = "El Tipo es obligatorio")
    @NotNull
    @Size(min = 2, max = 50)
    var tipo: String? = null

}