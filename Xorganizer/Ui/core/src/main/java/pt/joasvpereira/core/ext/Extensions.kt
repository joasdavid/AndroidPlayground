package pt.joasvpereira.core.ext

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream
import kotlin.math.roundToInt

fun Bitmap.toBase64String(
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG,
    quality: Int = 10
) : String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    compress(format, quality, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

fun Bitmap.compressSize(maxSize: Int = 500000) : Bitmap {
    val decoded = Base64.decode(compressBitmap(this, maxSize), Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(decoded, 0 , decoded.size)
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