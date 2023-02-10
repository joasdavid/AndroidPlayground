package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.repository.BoxDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.domain.usecase.BaseUseCase
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.Params

data class DeleteBoxParam(val id: Int) : Params()

interface IDeleteBoxUseCase : BaseUseCaseSync<DeleteBoxParam, Unit>

class DeleteBoxUseCase(private val boxDataSource: BoxDataSource) : IDeleteBoxUseCase {
    override suspend fun execute(params: DeleteBoxParam) = withContext(Dispatchers.IO) {
        boxDataSource.deleteBox(params.id)
    }
}