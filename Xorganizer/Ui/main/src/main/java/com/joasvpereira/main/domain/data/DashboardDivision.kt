package com.joasvpereira.main.domain.data

import com.joasvpereira.main.presentation.icons.DivisionIcon
import pt.joasvpereira.coreui.ThemeOption

data class DashboardDivision(
    val id: Int,
    val name: String,
    val description: String?,
    val icon: DivisionIcon,
    val boxCount: Int,
    val itemCount: Int,
    val themeOption: ThemeOption = ThemeOption.THEME_DEFAULT
)
