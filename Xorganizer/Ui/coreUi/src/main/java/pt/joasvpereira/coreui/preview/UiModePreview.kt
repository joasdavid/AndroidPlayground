package pt.joasvpereira.coreui.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mod", uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class UiModePreview