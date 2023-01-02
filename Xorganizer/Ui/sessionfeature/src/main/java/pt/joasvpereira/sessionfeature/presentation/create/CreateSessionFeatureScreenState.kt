package pt.joasvpereira.sessionfeature.presentation.create

import android.graphics.Bitmap
import android.graphics.Picture
import androidx.compose.runtime.MutableState

data class CreateSessionFeatureScreenState(
    val bitmap: Bitmap? = null,
    val sessionName: String = "",
    val isLoading: Boolean = false,
    val isButtonEnabled: Boolean = false
)
