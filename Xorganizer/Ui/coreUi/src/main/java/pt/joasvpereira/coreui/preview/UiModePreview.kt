package pt.joasvpereira.coreui.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import pt.joasvpereira.coreui.ThemeOption

@Preview(name = "Dark Mode", group = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Light Mode", group = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO)
annotation class UiModePreview

class ThemesProvider: PreviewParameterProvider<ThemeOption> {
    override val values = ThemeOption.values().asSequence()
}