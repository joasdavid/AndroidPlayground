package com.joasvpereira.main.compose.dashboard

import com.joasvpereira.main.domain.data.DashboardDivision
import com.joasvpereira.main.presentation.icons.DivisionIcons
import pt.joasvpereira.coreui.theme.ThemeOption

object PreviewData {
    internal val divisions = listOf(
        DashboardDivision(
            id = 0,
            name = "Room on the test long",
            description = "Home Office",
            icon = DivisionIcons.desk,
            boxCount = 0,
            itemCount = 0,
            themeOption = ThemeOption.THEME_BLUE
        ),
        DashboardDivision(
            id = 1,
            name = "Garden",
            description = null,
            icon = DivisionIcons.garden,
            boxCount = 0,
            itemCount = 0,
            themeOption = ThemeOption.THEME_DEFAULT
        ),
        DashboardDivision(
            id = 2,
            name = "Hallway",
            description = "",
            icon = DivisionIcons.hanger,
            boxCount = 0,
            itemCount = 0,
            themeOption = ThemeOption.THEME_GREEN
        ),
        DashboardDivision(
            id = 3,
            name = "Casa de banho quadrado",
            description = "",
            icon = DivisionIcons.bathtub,
            boxCount = 0,
            itemCount = 0,
            themeOption = ThemeOption.THEME_GREEN
        )
    )
}