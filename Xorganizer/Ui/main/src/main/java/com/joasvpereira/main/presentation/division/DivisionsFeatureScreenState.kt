package com.joasvpereira.main.presentation.division

import com.joasvpereira.main.compose.division.DivisionCreateButtonsState
import com.joasvpereira.main.domain.data.DivisionThemed
import com.joasvpereira.main.domain.data.DivisionElement

data class DivisionsFeatureScreenState(
    val division: DivisionThemed,
    val listContent: List<DivisionElement> = emptyList(),
    val nrOfBoxes: Int = 0,
    val nrOfItems: Int = 0,
    val isLoading: Boolean = false,
    val createButtonsState: DivisionCreateButtonsState = DivisionCreateButtonsState(),
    val createItem: CreateItem = CreateItem(name = "", description = "", isVisible = false),
    val createBox: CreateBox = CreateBox(name = "", description = "", isVisible = false),
    val filter: Filter = Filter(selectedFilter = 0, isVisible = false)
) {

    data class Filter(
        val selectedFilter: Int = 0,
        val isVisible: Boolean
    )

    data class CreateItem(
        val name: String,
        val description: String,
        val isVisible: Boolean
    )

    data class CreateBox(
        val name: String,
        val description: String,
        val isVisible: Boolean
    )
}
