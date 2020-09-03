package com.cdm.sig.models

import com.cdm.sig.models.integrations.ERole
import javax.persistence.*

@Entity
@Table(name = "roles")
class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    var name: ERole? = null
}