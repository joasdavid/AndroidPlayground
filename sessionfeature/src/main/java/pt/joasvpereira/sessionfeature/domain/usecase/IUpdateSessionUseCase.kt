package pt.joasvpereira.sessionfeature.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.domain.data.SessionItem
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.Params
import pt.joasvpereira.core.ext.compressSize
import pt.joasvpereira.core.ext.toBase64String
import pt.joasvpereira.core.repository.local.entities.Session
import pt.joasvpereira.sessionfeature.repository.SessionDataSource

data class UpdateSessionParams(val sessionItem: SessionItem) : Params

interface IUpdateSessionUseCase : BaseUseCaseSync<UpdateSessionParams, Unit>

class UpdateSessionUseCase(private val sessionsDataSource: SessionDataSource) : IUpdateSessionUseCase {

    override suspend fun execute(params: UpdateSessionParams) = withContext(Dispatchers.IO) {
        sessionsDataSource.updateNewSession(mapSession(params.sessionItem))
    }

    private fun mapSession(obj: SessionItem) = Session(
        id = if (obj.id == -1) null else obj.id,
        displayName = obj.name,
        image = obj.image?.compressSize()?.toBase64String(),
    )
}
