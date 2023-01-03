package pt.joasvpereira.sessionfeature.domain.usecase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
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
            var bitmap: Bitmap? = null
            it.image?.let { bitmapStringInBase64 ->
                val decoded = Base64.decode(bitmapStringInBase64, Base64.DEFAULT)
                bitmap = BitmapFactory.decodeByteArray(decoded, 0 , decoded.size)
            }
            SessionItem(id = it.id!!, name = it.displayName, image = bitmap)
        }
    }
}