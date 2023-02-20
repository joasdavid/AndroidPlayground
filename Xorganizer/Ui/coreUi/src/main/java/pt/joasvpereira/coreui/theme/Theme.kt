package pt.joasvpereira.coreui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.joasvpereira.loggger.extentions.logThis
import pt.joasvpereira.core.settings.domain.data.ThemePreference

//region data objects
data class Theme(
    val color: ColorScheme,
    val typography: Typography = AppTypography
)

enum class ThemeOption(val id: Int) {
    THEME_DEFAULT(0),
    THEME_BLUE(1),
    THEME_GREEN(2);

    companion object {
        fun getBy(id: Int): ThemeOption {
            ThemeOption.values().forEach {
                if (id == it.id)
                    return it
            }
            return THEME_DEFAULT
        }
    }
}

fun getAllThemesDetails() = listOf(
    Pair(ThemeOption.THEME_DEFAULT,"System Theme"),
    Pair(ThemeOption.THEME_BLUE,"Blue Theme"),
    Pair(ThemeOption.THEME_GREEN,"Green Theme")
)
//endregion

//region Composables

val LocalThemeConfig = compositionLocalOf { ThemePreference(isMaterialYouEnabled = false, mode =ThemePreference.ThemeMode.DEFAULT) }

@Composable
fun ThemePreference.ThemeMode.isDarkTheme() = when(this) {
    ThemePreference.ThemeMode.DEFAULT -> isSystemInDarkTheme()
    ThemePreference.ThemeMode.LIGHT -> false
    ThemePreference.ThemeMode.DARK -> true
}

@Composable
fun DynamicTheme(
    option: ThemeOption = ThemeOption.THEME_DEFAULT,
    isDarkTheme: Boolean = LocalThemeConfig.current.mode.isDarkTheme(),
    isDynamicColor: Boolean = LocalThemeConfig.current.isMaterialYouEnabled,
    content: @Composable() () -> Unit
) {
    """DynamicTheme configs are ${LocalThemeConfig.current}
        |isDarkTheme = $isDarkTheme
        |isDynamicColor = $isDynamicColor
    """.trimMargin().logThis(tag = "themeTest")
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
        isDarkTheme = isDarkTheme,
        content = content
    )
}

@Composable
private fun ApplyTheme(
    parameters: Theme,
    isDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    ApplySystemColor(
        color = Color.Transparent,
        isDarkTheme = isDarkTheme
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