package pt.joasvpereira.xorganizer.domain.usecase.session

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.joasvpereira.xorganizer.domain.model.SessionData
import pt.joasvpereira.xorganizer.domain.repo.SessionDataSource
import pt.joasvpereira.xorganizer.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.xorganizer.domain.usecase.Params
import pt.joasvpereira.xorganizer.repository.CurrentSession

data class SessionIdParam(val id: Int) : Params()

interface ISessionUseCase : BaseUseCaseSync<SessionIdParam, SessionData?>

class SessionUseCase(private val sessionsDataSource: SessionDataSource) : ISessionUseCase {

    override suspend fun execute(params: SessionIdParam): SessionData? = withContext(Dispatchers.IO) {
        kotlin.runCatching {
            sessionsDataSource.geSession(params.id)?.let {
                SessionData(it.id!!, it.displayName)
            }
        }.getOrNull()
    }
}