package com.joasvpereira.main.domain.usecase.division

import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.Params

data class CreateBoxParam(
    val name: String,
    val description: String,
    val parentId: Int
): Params()

interface ICreateBoxUseCase : BaseUseCaseSync<CreateBoxParam, Unit>