package com.cdm.sig.models

import com.cdm.sig.models.integrations.Afp
import com.cdm.sig.models.integrations.Arl
import com.cdm.sig.models.integrations.CajaComFamiliar
import com.cdm.sig.models.integrations.Eps
import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.Type;

import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull


@Entity
@Table(name = "empleados")
class Empleado {

    @Id
    @NotEmpty
    var cedula: String? = null

    @Column
    @NotEmpty
    var nombres: String? = null

    @Column
    @NotEmpty
    var apellidos: String? = null

    @Column
    @NotEmpty
    var genero: String? = null

    @Column
    @NotEmpty
    var fechaNacimiento: String? = null

    @Column
    @NotEmpty
    var tipoSangre: String? = null

    @Column
    @NotEmpty
    var direccion: String? = null

    @Column
    @NotEmpty
    var municipio: String? = null

    @Column
    @NotEmpty
    var telefono: String? = null

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    var eps: Eps? = null

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    var afp: Afp? = null

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    var arl: Arl? = null

    @OneToOne(fetch = FetchType.LAZY)
    var cajaComFamiliar: CajaComFamiliar? = null

    @Column
    var alergia: String? = null

    @Column
    var medimentos: String? = null

    @Column
    var enCasoEmergencia: String? = null

    @Column
    var parentesco: String? = null

    @Column
    var telEmergencia: String? = null

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @JsonIgnore
    var foto: ByteArray? = null



    fun getFotoHashCode(): Int? {
        return if (foto != null){
            this.foto.hashCode()
        }else null

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) { return true }
        if (other !is Empleado) { return false }

        return cedula != null && cedula == other.cedula
    }
}