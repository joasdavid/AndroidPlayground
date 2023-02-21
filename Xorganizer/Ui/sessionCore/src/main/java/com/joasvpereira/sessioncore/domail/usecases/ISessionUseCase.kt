package com.joasvpereira.sessioncore.domail.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.domain.data.SessionItem
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.Params
import pt.joasvpereira.core.ext.toBitmap
import pt.joasvpereira.sessionfeature.repository.SessionDataSource

data class SessionIdParam(val id: Int) : Params()

interface ISessionUseCase : BaseUseCaseSync<SessionIdParam, Flow<SessionItem>>

class SessionUseCase(private val sessionsDataSource: SessionDataSource) : ISessionUseCase {

    override suspend fun execute(params: SessionIdParam) = withContext(Dispatchers.IO) {
        sessionsDataSource.geSession(params.id).map {
            SessionItem(
                id = it.id!!,
                name = it.displayName,
                image = it.image?.toBitmap()
            )
        }
    }
}