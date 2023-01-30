package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.repository.DivisionDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.repository.local.entities.Division

class UpdateDivisionUseCase(
    private val dataSource: DivisionDataSource,
    private val sessionId: Int
) : IUpdateDivisionUseCase {
    override suspend fun execute(params: UpdateDivisionParam) = withContext(Dispatchers.IO) {
        dataSource.updateDivision(
            Division(
                id = params.id,
                name = params.name,
                description = params.description ?: "",
                iconId = params.icon.resId,
                themeId = params.themeId,
                sessionId = sessionId
            )
        )
    }
}