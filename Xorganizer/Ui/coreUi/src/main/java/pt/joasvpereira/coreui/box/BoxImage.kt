package pt.joasvpereira.coreui.box

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.R
import pt.joasvpereira.coreui.ThemeOption
import pt.joasvpereira.coreui.preview.ThemesProvider
import pt.joasvpereira.coreui.preview.UiModePreview

@Composable
fun BoxImage(modifier: Modifier = Modifier, tint: Color = MaterialTheme.colorScheme.onPrimaryContainer) {
    Image(
        painter = painterResource(id = R.drawable.ic_box_3),
        contentDescription = "",
        colorFilter = ColorFilter.tint(tint),
        modifier = modifier
    )
}

@Preview(group = "Single")
@Composable
private fun BoxImagePreview() {
    DynamicTheme {
        BoxImage()
    }
}

@UiModePreview
@Composable
private fun BoxImageThemedPreview(@PreviewParameter(ThemesProvider::class) theme: ThemeOption) {
    DynamicTheme(theme) {
        BoxImage()
    }
}