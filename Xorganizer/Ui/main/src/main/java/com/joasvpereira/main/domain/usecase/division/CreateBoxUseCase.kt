package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.repository.BoxDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.repository.local.entities.Box

class CreateBoxUseCase(val boxDataSource: BoxDataSource): ICreateBoxUseCase {
    override suspend fun execute(params: CreateBoxParam) = withContext(Dispatchers.IO) {
        boxDataSource.createNewBox(
            Box(
                id = null,
                name = params.name,
                description = params.description,
                parentDivisionId = params.parentId
            )
        )
    }
}