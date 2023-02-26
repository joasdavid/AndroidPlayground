package com.joasvpereira.main.presentation.details

import com.joasvpereira.main.domain.data.ItemDetail

data class ItemDetailScreenState(
    val itemDetail: ItemDetail = emptyDetails,
    val isLoading: Boolean = true,
)

private val emptyDetails = ItemDetail(
    id = -1,
    name = "",
    description = "",
    parentDivision = ItemDetail.ParentDivision(
        id = 0,
        name = "",
        divisionIcon = null,
        themeOption = null,
    ),
    parentBox = null,
)
