package pt.joasvpereira.sessionfeature.presentation.create

import android.R.attr.bitmap
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Base64
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.repository.local.entities.Session
import pt.joasvpereira.sessionfeature.repository.SessionDataSource
import java.io.ByteArrayOutputStream


class CreateSessionFeatureScreenViewModel(val dataSource: SessionDataSource): ViewModel() {

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

    fun save() {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.Default) {
            var bitmapString : String? = null
            state.bitmap?.let {
                val byteArrayOutputStream = ByteArrayOutputStream()
                it.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
                val byteArray = byteArrayOutputStream.toByteArray()
                bitmapString = Base64.encodeToString(byteArray, Base64.DEFAULT)
            }
            dataSource.createNewSession(
                Session(
                    id = null,
                    displayName = state.sessionName,
                    image = bitmapString
                )
            )
            withContext(Dispatchers.Main) {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }
}