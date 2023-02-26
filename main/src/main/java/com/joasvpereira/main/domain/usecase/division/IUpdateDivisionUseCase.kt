package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.presentation.icons.DivisionIcon
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync

class UpdateDivisionParam(
    val id: Int,
    name: String,
    description: String?,
    icon: DivisionIcon,
    themeId: Int,
) : CreateDivisionParams(
    name = name,
    description = description,
    icon = icon,
    themeId = themeId,
)

interface IUpdateDivisionUseCase : BaseUseCaseSync<UpdateDivisionParam, Unit>
