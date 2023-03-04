package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.repository.ItemDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.Params

data class DeleteItemParam(val id: Int) : Params

interface IDeleteItemUseCase : BaseUseCaseSync<DeleteItemParam, Unit>

class DeleteItemUseCase(private val itemDataSource: ItemDataSource) : IDeleteItemUseCase {
    override suspend fun execute(params: DeleteItemParam) = withContext(Dispatchers.IO) {
        itemDataSource.deleteItem(params.id)
    }
}
