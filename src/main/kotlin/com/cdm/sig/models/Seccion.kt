package com.cdm.sig.models

import com.cdm.sig.models.integrations.ESeccion
import javax.persistence.*

@Entity
@Table(name = "secciones")
class Seccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCargo : Long? = null

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    var nombre: ESeccion? = null
}