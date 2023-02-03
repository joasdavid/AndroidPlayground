package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.domain.data.DivisionElements
import kotlinx.coroutines.flow.Flow
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync

interface IGetDivisionElementsUseCase : BaseUseCaseSync<DivisionIdParam, Flow<DivisionElements>>