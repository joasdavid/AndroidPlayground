package com.joasvpereira.main.domain.data

import com.joasvpereira.main.presentation.icons.DivisionIcon
import pt.joasvpereira.coreui.ThemeOption

abstract class DivisionData(
    val id: Int,
    val name: String,
    val description: String?,
    val icon: DivisionIcon
)
