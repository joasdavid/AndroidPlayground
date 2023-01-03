package pt.joasvpereira.sessionfeature.presentation.create

import android.R.attr.bitmap
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import kotlin.math.roundToInt


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

    private fun compressBitmap(bitmap: Bitmap, maxSize: Int) : String {
            val bitmapCompressed = Bitmap.createScaledBitmap(bitmap, (bitmap.width *0.8).roundToInt(), (bitmap.width *0.8).roundToInt(), true)
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmapCompressed.compress(Bitmap.CompressFormat.PNG, 10, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            return if (byteArray.size <= maxSize) Base64.encodeToString(byteArray, Base64.DEFAULT) else compressBitmap(
                bitmapCompressed,
                maxSize
            )
    }

    fun save() {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.Default) {
            var bitmapString : String? = state.bitmap?.let { compressBitmap(bitmap = it, 500000) }
            dataSource.createNewSession(
                Session(
                    id = null,
                    displayName = state.sessionName,
                    image = bitmapString
                )
            )
            withContext(Dispatchers.Main) {
                _state.value = _state.value.copy(isLoading = false, saveState = SaveState.Success)
            }
        }
    }
}