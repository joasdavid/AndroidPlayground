package pt.joasvpereira.coreui.preview

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import org.jetbrains.annotations.TestOnly
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.theme.LocalThemeConfig
import pt.joasvpereira.coreui.theme.ThemeOption
import pt.joasvpereira.coreui.theme.isDarkTheme
import pt.joasvpereira.coreui.util.WindowSizeHelper

@Composable
fun PreviewWrapper(
    content: @Composable () -> Unit,
) {
    _initPreviewHelpers()
    content()
}

@Composable
fun PreviewWrapperWithTheme(
    option: ThemeOption = ThemeOption.THEME_DEFAULT,
    isDarkTheme: Boolean = LocalThemeConfig.current.mode.isDarkTheme(),
    isDynamicColor: Boolean = LocalThemeConfig.current.isMaterialYouEnabled,
    content: @Composable () -> Unit,
) {
    _initPreviewHelpers()
    DynamicTheme(
        option = option,
        isDarkTheme = isDarkTheme,
        isDynamicColor = isDynamicColor,
        content = content,
    )
}

@TestOnly
@SuppressLint("ComposableNaming")
@Composable
private fun _initPreviewHelpers() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    WindowSizeHelper.load(dpWidth = screenWidth, dpHeight = screenHeight)
}
