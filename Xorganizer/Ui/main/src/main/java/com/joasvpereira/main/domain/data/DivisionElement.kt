package com.joasvpereira.main.domain.data

sealed interface DivisionElement {
    data class Item(val id: Int, val name: String) : DivisionElement
    data class Box(val id: Int, val name: String) : DivisionElement
}