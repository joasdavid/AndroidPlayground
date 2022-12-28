package pt.joasvpereira.sessionfeature.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.EmptyParams
import pt.joasvpereira.sessionfeature.domain.data.SessionItem
import pt.joasvpereira.sessionfeature.repository.SessionDataSource

interface ISessionUseCase : BaseUseCaseSync<EmptyParams, SessionItem>

class SessionUseCase() : ISessionUseCase {

    override suspend fun execute(params: EmptyParams) = withContext(Dispatchers.IO) {
        SessionItem(id = 0, name = "JVP", image = null)
    }
}