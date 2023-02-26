package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.repository.ItemDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.repository.local.entities.Item

class CreateItemUseCase(private val itemDataSource: ItemDataSource) : ICreateItemUseCase {
    override suspend fun execute(params: CreateItemParam) = withContext(Dispatchers.IO) {
        itemDataSource.createNewItem(
            Item(
                id = null,
                name = params.name.trim(),
                description = params.description.trim(),
                parentDivisionId = params.parentId,
                parentBoxId = params.parentBoxId,
            ),
        )
    }
}
