package com.cdm.sig.models

import com.cdm.sig.models.integrations.Item
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table
class EntregaDyE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idEntregaDyE: Long? = null

    @Column
    @NotNull
    var fechaEntregaDyE: LocalDate? = null

    @Column
    @NotEmpty
    var descripcion: String? = null

    @Column
    @NotEmpty
    var tipo: String? = null

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    var empleado: Empleado? = null

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "entrega_dye_items",
            joinColumns = [JoinColumn(name = "entrega_dye_id_entrega_dye")],
            inverseJoinColumns = [JoinColumn(name = "items_id_item")])
    var items: MutableList<Item>? = null

    init {
        items = ArrayList()
    }

    fun addItem(item: Item) {
        this.items!!.add(item)
    }

    fun removeItem(item: Item) {
        this.items!!.remove(item)
    }
}
