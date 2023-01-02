package pt.joasvpereira.sessionfeature.presentation.create

import android.content.Context
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CreateSessionFeatureScreenViewModel: ViewModel() {

    private var _state = mutableStateOf(CreateSessionFeatureScreenState())
    val state : CreateSessionFeatureScreenState
        get() = _state.value


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

    fun save() {}
}