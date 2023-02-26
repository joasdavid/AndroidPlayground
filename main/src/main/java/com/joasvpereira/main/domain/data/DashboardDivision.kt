package com.joasvpereira.main.domain.data

import com.joasvpereira.main.presentation.icons.DivisionIcon
import pt.joasvpereira.coreui.theme.ThemeOption

class DashboardDivision(
    id: Int,
    name: String,
    description: String?,
    icon: DivisionIcon,
    val boxCount: Int,
    val itemCount: Int,
    val themeOption: ThemeOption = ThemeOption.THEME_DEFAULT,
) : DivisionData(id = id, name = name, description = description, icon = icon)
