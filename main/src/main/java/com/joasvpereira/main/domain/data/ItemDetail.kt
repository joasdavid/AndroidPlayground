package com.joasvpereira.main.domain.data

import com.joasvpereira.main.presentation.icons.DivisionIcon
import pt.joasvpereira.coreui.theme.ThemeOption

data class ItemDetail(
    val id: Int,
    val name: String,
    val description: String,
    val parentDivision: ParentDivision,
    val parentBox: ParentBox?,
) {
    data class ParentDivision(
        val id: Int,
        val name: String,
        val divisionIcon: DivisionIcon?,
        val themeOption: ThemeOption?,
    )

    data class ParentBox(
        val id: Int,
        val name: String,
    )
}
