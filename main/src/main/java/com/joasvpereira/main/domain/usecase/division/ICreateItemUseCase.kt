package com.joasvpereira.main.domain.usecase.division

import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.Params

data class CreateItemParam(
    val name: String,
    val description: String,
    val parentId: Int,
    val parentBoxId: Int? = null,
) : Params

interface ICreateItemUseCase : BaseUseCaseSync<CreateItemParam, Unit>
