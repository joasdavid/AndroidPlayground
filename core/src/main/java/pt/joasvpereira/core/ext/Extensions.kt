package pt.joasvpereira.core.ext

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream
import kotlin.math.roundToInt

fun Bitmap.toBase64String(
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG,
    quality: Int = 10,
): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    compress(format, quality, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

fun String.toBitmap(): Bitmap {
    val decoded = Base64.decode(this, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(decoded, 0, decoded.size)
}

fun Bitmap.compressSize(maxSize: Int = 500000): Bitmap {
    val decoded = Base64.decode(compressBitmap(this, maxSize), Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(decoded, 0, decoded.size)
}

const val COMPRESSION_RATIO = 0.8
const val QUALITY = 10
private fun compressBitmap(bitmap: Bitmap, maxSize: Int): String {
    val bitmapCompressed = Bitmap.createScaledBitmap(
        bitmap,
        (bitmap.width * COMPRESSION_RATIO).roundToInt(),
        (bitmap.width * COMPRESSION_RATIO).roundToInt(),
        true
    )
    val byteArrayOutputStream = ByteArrayOutputStream()

    bitmapCompressed.compress(
        Bitmap.CompressFormat.PNG,
        QUALITY,
        byteArrayOutputStream
    )

    val byteArray = byteArrayOutputStream.toByteArray()
    return if (byteArray.size <= maxSize) {
        Base64.encodeToString(byteArray, Base64.DEFAULT)
    } else {
        compressBitmap(
            bitmapCompressed,
            maxSize,
        )
    }
}
