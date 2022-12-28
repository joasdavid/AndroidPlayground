package pt.joasvpereira.sessionfeature.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.EmptyParams
import pt.joasvpereira.sessionfeature.domain.data.SessionItem
import pt.joasvpereira.sessionfeature.repository.SessionDataSource

interface ISessionsUseCase : BaseUseCaseSync<EmptyParams, List<SessionItem>>

class SessionsUseCase(private val sessionsDataSource: SessionDataSource) : ISessionsUseCase {

    override suspend fun execute(params: EmptyParams) = withContext(Dispatchers.IO) {
        sessionsDataSource.geSessions().map {
            SessionItem(id = it.id!!, name = it.displayName, image = null)
        }
    }
}