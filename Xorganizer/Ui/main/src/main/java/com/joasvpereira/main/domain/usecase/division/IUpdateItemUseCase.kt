package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.repository.ItemDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.Params

data class UpdateItemParam(val id: Int, val name: String? = null, val description: String? = null) : Params()

interface IUpdateItemUseCase : BaseUseCaseSync<UpdateItemParam, Unit>

class UpdateItemUseCase(private val itemDataSource: ItemDataSource) : IUpdateItemUseCase {
    override suspend fun execute(params: UpdateItemParam): Unit = withContext(Dispatchers.IO) {
        itemDataSource.getItem(params.id)?.let {
            val updatedItem = it.copy(
                name = params.name?.trim() ?: it.name,
                description = params.description?.trim() ?: it.description
            )
            itemDataSource.updateItem(updatedItem)
        }
    }
}