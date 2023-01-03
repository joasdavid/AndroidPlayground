package pt.joasvpereira.sessionfeature.domain.usecase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.EmptyParams
import pt.joasvpereira.core.domain.usecase.Params
import pt.joasvpereira.core.ext.compressSize
import pt.joasvpereira.core.ext.toBase64String
import pt.joasvpereira.core.repository.local.entities.Session
import pt.joasvpereira.sessionfeature.domain.data.SessionItem
import pt.joasvpereira.sessionfeature.repository.SessionDataSource

data class CreateSessionParams(val sessionItem: SessionItem) : Params()

interface ICreateSessionUseCase : BaseUseCaseSync<CreateSessionParams, Unit>

class CreateSessionUseCase(private val sessionsDataSource: SessionDataSource) : ICreateSessionUseCase {

    override suspend fun execute(params: CreateSessionParams) = withContext(Dispatchers.IO) {
        sessionsDataSource.createNewSession(mapSession(params.sessionItem))
    }

    private fun mapSession(obj: SessionItem) = Session(
        id = if (obj.id == -1) null else obj.id,
        displayName = obj.name,
        image = obj.image?.compressSize()?.toBase64String()
    )
}