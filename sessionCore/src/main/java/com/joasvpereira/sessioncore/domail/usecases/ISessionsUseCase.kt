package com.joasvpereira.sessioncore.domail.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.domain.data.SessionItem
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.EmptyParams
import pt.joasvpereira.core.ext.toBitmap
import pt.joasvpereira.sessionfeature.repository.SessionDataSource

interface ISessionsUseCase : BaseUseCaseSync<EmptyParams, Flow<List<SessionItem>>>

class SessionsUseCase(private val sessionsDataSource: SessionDataSource) : ISessionsUseCase {

    override suspend fun execute(params: EmptyParams) = withContext(Dispatchers.IO) {
        sessionsDataSource.geSessions().map { list ->
            list.map {
                SessionItem(id = it.id!!, name = it.displayName, image = it.image?.toBitmap())
            }
        }
    }
}
