package pt.joasvpereira.xorganizer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

enum class ThemeOption {
    THEME_DEFAULT,
    THEME_BLUE,
    THEME_GREEN;
}

@Composable
fun DynamicTheme(
    option: ThemeOption = ThemeOption.THEME_DEFAULT,
    isToApplyToSystemUi: Boolean = false,
    content: @Composable() () -> Unit
) {
    when (option) {
        ThemeOption.THEME_BLUE -> BlueTheme(content = content)
        ThemeOption.THEME_GREEN -> GreenTheme(content = content)
        else -> DefaultTheme(content = content, isToApplyToSystemUi = isToApplyToSystemUi)
    }
}

@Composable
fun SetupSystemColor(
    color: Color = MaterialTheme.colorScheme.background,
    useDarkIcons: Boolean = !isSystemInDarkTheme()
) {
        val systemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(
                color = color,
                darkIcons = useDarkIcons
            )
}