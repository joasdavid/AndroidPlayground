package com.joasvpereira.main.presentation.icons

import pt.joasvpereira.main.R

data class DivisionIcon(
    val name: String,
    val resId: Int,
)

object DivisionIcons {
    val garden = DivisionIcon("garden", R.drawable.ic_garden)
    val downstairs = DivisionIcon("downstairs", R.drawable.ic_downstairs)
    val washer = DivisionIcon("washer", R.drawable.ic_washer)
    val laundry = DivisionIcon("laundry", R.drawable.ic_laundry)
    val hanger = DivisionIcon("hanger", R.drawable.ic_hanger)
    val washroom = DivisionIcon("washroom", R.drawable.ic_washroom)
    val home = DivisionIcon("home =", R.drawable.ic_home)
    val livingRoom = DivisionIcon("livingRoom", R.drawable.ic_livingroom)
    val hanger2 = DivisionIcon("hanger2", R.drawable.ic_hanger_2)
    val livingRoom2 = DivisionIcon("livingRoom2", R.drawable.ic_livingroom_2)
    val table = DivisionIcon("table", R.drawable.ic_table)
    val washroom2 = DivisionIcon("washroom2", R.drawable.ic_washroom_2)
    val lampDesk = DivisionIcon("lampDesk", R.drawable.ic_lamp_desk)
    val kitchenOven = DivisionIcon("kitchenOven", R.drawable.ic_kitchen_oven)
    val table2 = DivisionIcon("table2", R.drawable.ic_table_2)
    val office_box = DivisionIcon("office_box", R.drawable.ic_office_box)
    val garage = DivisionIcon("garage", R.drawable.ic_garage)
    val garageWrench = DivisionIcon("garageWrench", R.drawable.ic_garage_wrench)
    val desk = DivisionIcon("desk =", R.drawable.ic_desk)
    val cutlery = DivisionIcon("cutlery", R.drawable.ic_cutlery)
    val couch = DivisionIcon("couch", R.drawable.ic_couch)
    val cactus = DivisionIcon("cactus", R.drawable.ic_cactus)
    val bathtub = DivisionIcon("bathtub", R.drawable.ic_bathtub)
    val attic = DivisionIcon("attic", R.drawable.ic_attic)

    val all = listOf(
        garden,
        downstairs,
        washer,
        laundry,
        hanger,
        washroom,
        home,
        livingRoom,
        hanger2,
        livingRoom2,
        table,
        washroom2,
        lampDesk,
        kitchenOven,
        table2,
        office_box,
        garage,
        garageWrench,
        desk,
        cutlery,
        couch,
        cactus,
        bathtub,
        attic,
    )

    fun getBy(name: String): DivisionIcon? =
        all.find {
            it.name.equals(name)
        }

    fun getBy(resId: Int): DivisionIcon? =
        all.find {
            it.resId == resId
        }

    fun getBy(name: String, orDefault: DivisionIcon): DivisionIcon =
        all.find {
            it.name.equals(name)
        } ?: orDefault
}
