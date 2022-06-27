package pt.joasvpereira.xorganizer.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

//region data objects
data class Theme(
    val color: ColorScheme,
    val typography: Typography = AppTypography
)

enum class ThemeOption {
    THEME_DEFAULT,
    THEME_BLUE,
    THEME_GREEN;
}

fun getAllThemesDetails() = listOf(
    Pair(ThemeOption.THEME_DEFAULT,"System Theme"),
    Pair(ThemeOption.THEME_BLUE,"Blue Theme"),
    Pair(ThemeOption.THEME_GREEN,"Green Theme")
)
//endregion

//region Composables
@Composable
fun DynamicTheme(
    option: ThemeOption = ThemeOption.THEME_DEFAULT,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    isDynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
    val themeToUse = when (option) {
        ThemeOption.THEME_BLUE -> blueTheme(isDarkTheme)
        ThemeOption.THEME_GREEN -> greenTheme(isDarkTheme)
        else -> defaultTheme(
            isDarkTheme = isDarkTheme,
            isDynamicColor = isDynamicColor
        )
    }

    ApplyTheme(
        parameters = themeToUse,
        content = content
    )
}

@Composable
private fun ApplyTheme(
    parameters: Theme,
    content: @Composable () -> Unit
) {
    ApplySystemColor(
        color = Color.Transparent,
        isDarkTheme = false
    )

    MaterialTheme(
        colorScheme = parameters.color,
        typography = parameters.typography,
        content = content
    )
}

@Composable
private fun ApplySystemColor(
    color: Color = MaterialTheme.colorScheme.background,
    isDarkTheme: Boolean
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = color,
        darkIcons = !isDarkTheme
    )
}
//endregion