package com.joasvpereira.main.domain.data

import pt.joasvpereira.coreui.theme.ThemeOption

data class ParentBox(
    val id: Int,
    val name: String,
    val description: String,
    val parentDivision: ParentDivision?,
) {
    data class ParentDivision(
        val id: Int,
        val themeOption: ThemeOption?
    )
}