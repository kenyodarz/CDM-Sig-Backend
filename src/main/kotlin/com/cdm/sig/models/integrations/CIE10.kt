package com.cdm.sig.models.integrations

import javax.persistence.*

@Entity
@Table(name = "cie10")
class CIE10 {

    @Id
    var idCIE10: String?=null

    @Column
    var descripcion: String?=null

    @Column
    var capitulo: String?=null

}