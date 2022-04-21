package pt.joasvpereira.xorganizer.ui.theme

import androidx.compose.runtime.Composable

enum class ThemeOption {
    THEME_DEFAULT,
    THEME_BLUE,
    THEME_GREEN;
}

@Composable
fun DynamicTheme(
    option: ThemeOption = ThemeOption.THEME_DEFAULT,
    content: @Composable() () -> Unit
) {
    when (option) {
        ThemeOption.THEME_BLUE -> BlueTheme(content = content)
        ThemeOption.THEME_GREEN -> GreenTheme(content = content)
        else -> DefaultTheme(content = content)
    }
}