package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.repository.DivisionDataSource
import pt.joasvpereira.core.domain.usecase.BaseUseCase
import pt.joasvpereira.core.domain.usecase.Params


class DeleteDivisionUseCase(private val dataSource: DivisionDataSource) : IDeleteDivisionUseCase {
    override suspend fun execute(params: DeleteDivisionParam) {
        dataSource.deleteDivision(params.id)
    }
}