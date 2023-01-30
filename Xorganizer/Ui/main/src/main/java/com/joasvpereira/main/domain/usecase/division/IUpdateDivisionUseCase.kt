package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.domain.data.DashboardDivision
import com.joasvpereira.main.presentation.icons.DivisionIcon
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.EmptyParams

class UpdateDivisionParam(
    val id: Int,
    name: String,
    description: String?,
    icon: DivisionIcon,
    themeId: Int
) : CreateDivisionParams(
    name = name,
    description = description,
    icon = icon,
    themeId = themeId
)

interface IUpdateDivisionUseCase : BaseUseCaseSync<UpdateDivisionParam, Unit>