package com.joasvpereira.main.domain.data

interface Element {
    val id: Int
    val name: String
    val description: String
}

sealed interface DivisionElement : Element {
    data class Item(override val id: Int, override val name: String, override val description: String = "") : DivisionElement
    data class Box(override val id: Int, override val name: String, override val description: String = "") : DivisionElement
}
