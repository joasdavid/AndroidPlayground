package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.domain.data.DivisionThemed
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.Params

data class DivisionIdParam(val id: Int) : Params

interface IDivisionUseCase : BaseUseCaseSync<DivisionIdParam, DivisionThemed>
