package pt.joasvpereira.xorganizer.domain.model

import pt.joasvpereira.xorganizer.domain.usecase.Params

data class DivisionCreationInfo(
    val name: String,
    val description: String,
    val themeId: Int,
): Params()