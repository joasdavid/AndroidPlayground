package com.joasvpereira.main.domain.data

import com.joasvpereira.main.presentation.icons.DivisionIcon
import pt.joasvpereira.coreui.ThemeOption

class DivisionThemed(
    id: Int,
    name: String,
    description: String?,
    icon: DivisionIcon,
    val themeOption: ThemeOption
) : DivisionData(
    id,
    name,
    description,
    icon
)
