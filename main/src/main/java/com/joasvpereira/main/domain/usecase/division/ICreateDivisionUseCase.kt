package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.presentation.icons.DivisionIcon
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.Params

open class CreateDivisionParams(
    val name: String,
    val description: String?,
    val icon: DivisionIcon,
    val themeId: Int,
) : Params

interface ICreateDivisionUseCase : BaseUseCaseSync<CreateDivisionParams, Unit>
