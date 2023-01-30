package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.repository.DivisionDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.repository.local.entities.Division

class CreateDivisionUseCase(
    private val dataSource: DivisionDataSource,
    private val sessionId: Int
    ) : ICreateDivisionUseCase {
    override suspend fun execute(params: CreateDivisionParams) = withContext(Dispatchers.IO) {
        dataSource.createNewDivision(
            Division(
                id = null,
                name = params.name,
                description = params.description ?: "",
                iconId = params.icon.resId,
                themeId = params.themeId,
                sessionId = sessionId
            )
        )
    }
}