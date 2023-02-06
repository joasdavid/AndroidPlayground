package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.domain.data.DivisionElements
import kotlinx.coroutines.flow.Flow
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.Params

data class GetDivisionElementsParams(
    val divisionId: Int,
    val filter: Filter = Filter.All
): Params() {
    sealed interface Filter {
        object All: Filter
        object OnlyItems: Filter
        object OnlyBoxes: Filter
    }
}

interface IGetDivisionElementsUseCase : BaseUseCaseSync<GetDivisionElementsParams, Flow<DivisionElements>>