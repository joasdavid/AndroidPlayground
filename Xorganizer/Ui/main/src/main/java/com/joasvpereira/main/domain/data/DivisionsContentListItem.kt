package com.joasvpereira.main.domain.data

sealed interface DivisionsContentListItem {
    data class Item(val id: Int, val name: String) : DivisionsContentListItem
    data class Box(val id: Int, val name: String) : DivisionsContentListItem
}