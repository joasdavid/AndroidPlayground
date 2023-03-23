package pt.joasvpereira.coreui.session

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.joasvpereira.lib.compose.namedshield.NamedShield
import pt.joasvpereira.coreui.R
import pt.joasvpereira.coreui.preview.ThemesProvider
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.theme.ThemeOption

@Composable
fun SessionIconHolder(
    sessionName: String,
    sessionImage: Bitmap?,
    modifier: Modifier = Modifier.size(40.dp)
) {
    if (sessionImage == null) {
        HolderWithoutImage(sessionName, modifier)
    } else {
        HolderWithImage(sessionImage, modifier)
    }
}

@Composable
private fun HolderWithImage(sessionImage: Bitmap, modifier: Modifier) {
    Image(
        bitmap = sessionImage.asImageBitmap(),
        contentDescription = "",
        modifier = modifier
            .clip(CircleShape)
            .border(1.dp, color = MaterialTheme.colorScheme.primaryContainer, shape = CircleShape),
    )
}

@Composable
private fun HolderWithoutImage(sessionName: String, modifier: Modifier) {
    NamedShield(
        text = sessionName,
        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
        borderSize = 0.dp,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun SessionIconHolderPreview() {
    DynamicTheme() {
        SessionIconHolder(
            sessionName = "this is a test",
            sessionImage = null,
        )
    }
}

@UiModePreview
@Composable
private fun SessionIconHolderPreview(@PreviewParameter(ThemesProvider::class) theme: ThemeOption) {
    DynamicTheme(theme) {
        SessionIconHolder(
            sessionName = "this is a test",
            sessionImage = null,
        )
    }
}

@SuppressLint("UseCompatLoadingForDrawables")
@Preview
@Composable
private fun SessionIconWithImageHolderPreview() {
    val bitmap = LocalContext.current.getDrawable(R.drawable.ic_upload)?.toBitmap()
    DynamicTheme() {
        SessionIconHolder(
            sessionName = "this is a test",
            sessionImage = bitmap,
        )
    }
}

@SuppressLint("UseCompatLoadingForDrawables")
@UiModePreview
@Composable
private fun SessionIconWithImageHolderPreview(@PreviewParameter(ThemesProvider::class) theme: ThemeOption) {
    val bitmap = LocalContext.current.getDrawable(R.drawable.ic_upload)?.toBitmap()
    DynamicTheme(theme) {
        SessionIconHolder(
            sessionName = "this is a test",
            sessionImage = bitmap,
        )
    }
}
