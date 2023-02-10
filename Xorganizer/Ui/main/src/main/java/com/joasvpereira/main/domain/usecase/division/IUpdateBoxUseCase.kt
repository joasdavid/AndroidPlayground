package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.repository.BoxDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.Params

data class UpdateBoxParam(val id: Int, val name: String? = null, val description: String? = null) : Params()

interface IUpdateBoxUseCase : BaseUseCaseSync<UpdateBoxParam, Unit>

class UpdateBoxUseCase(private val boxDataSource: BoxDataSource) : IUpdateBoxUseCase {
    override suspend fun execute(params: UpdateBoxParam): Unit = withContext(Dispatchers.IO) {
        boxDataSource.getBox(params.id)?.let {
            val updatedBox = it.copy(
                name = params.name?.trim() ?: it.name,
                description = params.description?.trim() ?: it.description
            )
            boxDataSource.updateBox(updatedBox)
        }
    }
}