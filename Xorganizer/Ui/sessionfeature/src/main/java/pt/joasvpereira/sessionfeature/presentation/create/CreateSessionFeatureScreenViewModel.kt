package pt.joasvpereira.sessionfeature.presentation.create

import android.content.Context
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.domain.usecase.EmptyParams
import pt.joasvpereira.sessionfeature.domain.data.SessionItem
import pt.joasvpereira.sessionfeature.domain.usecase.CreateSessionParams
import pt.joasvpereira.sessionfeature.domain.usecase.ICreateSessionUseCase
import pt.joasvpereira.sessionfeature.domain.usecase.ISessionUseCase
import pt.joasvpereira.sessionfeature.domain.usecase.SessionIdParam


open class CreateSessionFeatureScreenViewModel(
    val sessionUseCase: ISessionUseCase,
    val createSessionUseCase: ICreateSessionUseCase,
) : ViewModel() {

    private var _state = mutableStateOf(CreateSessionFeatureScreenState())
    val state: CreateSessionFeatureScreenState
        get() = _state.value

    private var originId = -1

    fun load(sessionItemId: Int) {
        if (sessionItemId <= 0) return
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.Main) {
            val sessionItem = sessionUseCase.execute(SessionIdParam(sessionItemId))
            sessionItem?.let {
                originId = it.id
                _state.value = _state.value.copy(
                    bitmap = it.image,
                    sessionName = it.name,
                    isLoading = false,
                    isButtonEnabled = true,
                    mode = Mode.Edit
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
            createSessionUseCase.execute(
                CreateSessionParams(
                    SessionItem(
                        id = originId,
                        name = state.sessionName,
                        image = state.bitmap
                    )
                )
            )
            withContext(Dispatchers.Main) {
                _state.value = _state.value.copy(isLoading = false, saveState = SaveState.Success)
            }
        }
    }
}