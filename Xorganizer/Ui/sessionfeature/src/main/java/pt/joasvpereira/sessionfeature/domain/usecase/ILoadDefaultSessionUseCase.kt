package pt.joasvpereira.sessionfeature.domain.usecase

import com.joasvpereira.loggger.extentions.logThis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.EmptyParams
import pt.joasvpereira.core.repository.CurrentSession


interface ILoadSessionUseCase : BaseUseCaseSync<EmptyParams, Boolean>

class LoadSessionUseCase(private val sessionsUseCase: ISessionsUseCase) : ILoadSessionUseCase {

    override suspend fun execute(params: EmptyParams): Boolean = withContext(Dispatchers.IO) {
        sessionsUseCase.execute(params).run {
            "Load session found $size sessions".logThis(tag = "JVP")
            if (size == 1) {
                CurrentSession.session = this.first().logThis(tag = "JVP") {
                    "load session = $it"
                }
                return@withContext true
            }

            return@withContext false
        }
    }
}