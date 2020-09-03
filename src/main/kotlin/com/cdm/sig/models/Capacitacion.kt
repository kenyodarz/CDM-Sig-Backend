package com.cdm.sig.models

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "capacitaciones")
class Capacitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCapacitacion: Long? = null

    @Column
    var tema: String? = null

    @Column
    var fecha: LocalDate? = null

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "capacitaciones_empleados",
            joinColumns = [JoinColumn(name = "capacitacion_id_capacitacion")],
            inverseJoinColumns = [JoinColumn(name = "empleados_cedula")])
    var empleados: MutableList<Empleado>? = null

    init {
        empleados = ArrayList<Empleado>()
    }

    fun addEmpleado(empleado: Empleado) {
        this.empleados!!.add(empleado)
    }

    fun removeEmpleado(empleado: Empleado){
        this.empleados!!.remove(empleado)
    }

}