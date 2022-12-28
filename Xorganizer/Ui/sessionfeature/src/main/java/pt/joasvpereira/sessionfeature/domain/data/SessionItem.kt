package pt.joasvpereira.sessionfeature.domain.data

import android.graphics.Bitmap

data class SessionItem(
    val id: Int,
    val name: String,
    val image: Bitmap? = null,
)
