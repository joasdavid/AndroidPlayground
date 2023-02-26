package pt.joasvpereira.sessionfeature.presentation.create

import android.content.Context
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joasvpereira.sessioncore.domail.usecases.ISessionUseCase
import com.joasvpereira.sessioncore.domail.usecases.SessionIdParam
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.domain.data.SessionItem
import pt.joasvpereira.sessionfeature.domain.usecase.CreateSessionParams
import pt.joasvpereira.sessionfeature.domain.usecase.ICreateSessionUseCase
import pt.joasvpereira.sessionfeature.domain.usecase.IDeleteSessionUseCase
import pt.joasvpereira.sessionfeature.domain.usecase.IUpdateSessionUseCase
import pt.joasvpereira.sessionfeature.domain.usecase.UpdateSessionParams

open class CreateSessionFeatureScreenViewModel(
    private val sessionUseCase: ISessionUseCase,
    private val createSessionUseCase: ICreateSessionUseCase,
    private val updateSessionUseCase: IUpdateSessionUseCase,
    private val deleteSessionUseCase: IDeleteSessionUseCase,
) : ViewModel() {

    private var _state = mutableStateOf(CreateSessionFeatureScreenState())
    val state: CreateSessionFeatureScreenState
        get() = _state.value

    private var originId = -1

    fun load(sessionItemId: Int) {
        if (sessionItemId <= 0) return
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.Main) {
            val sessionItem = sessionUseCase.execute(SessionIdParam(sessionItemId)).first()
            sessionItem.let {
                originId = it.id
                _state.value = _state.value.copy(
                    bitmap = it.image,
                    sessionName = it.name,
                    isLoading = false,
                    isButtonEnabled = true,
                    mode = Mode.Edit,
                )
            }
        }
    }

    fun processContentResult(context: Context, uri: Uri?) {
        uri?.let {
            val bitmapValue = if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images
                    .Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder
                    .createSource(context.contentResolver, it)
                ImageDecoder.decodeBitmap(source)
            }
            _state.value = _state.value.copy(bitmap = bitmapValue)
        }
    }

    fun removeImage() {
        _state.value = _state.value.copy(bitmap = null)
    }

    fun changeName(name: String) {
        _state.value = _state.value.copy(sessionName = name, isButtonEnabled = name.isNotEmpty())
    }

    fun save() {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.Default) {
            if (state.mode == Mode.Create) {
                createSessionUseCase.execute(
                    CreateSessionParams(
                        SessionItem(
                            id = originId,
                            name = state.sessionName,
                            image = state.bitmap,
                        ),
                    ),
                )
            } else {
                updateSessionUseCase.execute(
                    UpdateSessionParams(
                        sessionItem = SessionItem(
                            id = originId,
                            name = state.sessionName,
                            image = state.bitmap,
                        ),
                    ),
                )
            }
            withContext(Dispatchers.Main) {
                _state.value = _state.value.copy(isLoading = false, saveState = SaveState.Success)
            }
        }
    }

    fun delete() {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.Main) {
            deleteSessionUseCase.execute(SessionIdParam(originId))
            _state.value = _state.value.copy(isLoading = false, saveState = SaveState.Success)
        }
    }
}
