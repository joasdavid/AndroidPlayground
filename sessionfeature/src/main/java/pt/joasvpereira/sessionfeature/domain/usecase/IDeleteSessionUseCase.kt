package pt.joasvpereira.sessionfeature.domain.usecase

import com.joasvpereira.sessioncore.domail.usecases.SessionIdParam
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.sessionfeature.repository.SessionDataSource

interface IDeleteSessionUseCase : BaseUseCaseSync<SessionIdParam, Unit>

class DeleteSessionUseCase(
    private val sessionsDataSource: SessionDataSource,
) : IDeleteSessionUseCase {

    override suspend fun execute(params: SessionIdParam) {
        withContext(Dispatchers.IO) {
            sessionsDataSource.deleteSession(params.id)
        }
    }
}
