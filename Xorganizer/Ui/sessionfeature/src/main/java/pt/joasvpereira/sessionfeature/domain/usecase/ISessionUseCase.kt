package pt.joasvpereira.sessionfeature.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.Params
import pt.joasvpereira.core.ext.toBitmap
import pt.joasvpereira.core.domain.data.SessionItem
import pt.joasvpereira.sessionfeature.repository.SessionDataSource

data class SessionIdParam(val id: Int) : Params()

interface ISessionUseCase : BaseUseCaseSync<SessionIdParam, SessionItem?>

class SessionUseCase(private val sessionsDataSource: SessionDataSource) : ISessionUseCase {

    override suspend fun execute(params: SessionIdParam) = withContext(Dispatchers.IO) {
        sessionsDataSource.geSession(params.id)?.let {
            SessionItem(
                id = it.id!!,
                name = it.displayName,
                image = it.image?.toBitmap()
            )
        }
    }
}