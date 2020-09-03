package com.cdm.sig.models

import com.cdm.sig.models.integrations.ECargo
import javax.persistence.*

@Entity
@Table(name = "cargos")
class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCargo : Long? = null

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    var nombre: ECargo? = null

}