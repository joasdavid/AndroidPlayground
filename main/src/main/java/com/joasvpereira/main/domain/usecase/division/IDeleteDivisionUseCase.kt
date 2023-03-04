package com.joasvpereira.main.domain.usecase.division

import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.Params

data class DeleteDivisionParam(val id: Int) : Params

interface IDeleteDivisionUseCase : BaseUseCaseSync<DeleteDivisionParam, Unit>
