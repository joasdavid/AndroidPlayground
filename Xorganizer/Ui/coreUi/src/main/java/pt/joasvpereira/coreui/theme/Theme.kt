package pt.joasvpereira.coreui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.joasvpereira.loggger.extentions.logThis
import kotlinx.coroutines.flow.first
import org.koin.androidx.compose.get
import pt.joasvpereira.core.settings.InternalThemePref
import pt.joasvpereira.core.settings.domain.data.ThemePreference
import pt.joasvpereira.core.settings.repository.ThemePreferencesDataSource
import pt.joasvpereira.coreui.theme.AppTypography
import pt.joasvpereira.coreui.theme.blueTheme
import pt.joasvpereira.coreui.theme.defaultTheme
import pt.joasvpereira.coreui.theme.greenTheme

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

@Composable
fun shouldDisplayDarkTheme() : Boolean =
    when(InternalThemePref.mode) {
        ThemePreference.ThemeMode.DEFAULT -> isSystemInDarkTheme()
        ThemePreference.ThemeMode.LIGHT -> false
        ThemePreference.ThemeMode.DARK -> true
    }.logThis {
        "shouldDisplayDarkTheme(InternalThemePref.mode = ${InternalThemePref.mode} || returning $it)"
    }

@Composable
fun shouldDisplayAsMaterialYou() : Boolean = InternalThemePref.isMaterialYou.logThis {
    "shouldDisplayAsMaterialYou(InternalThemePref.isMaterialYou = ${InternalThemePref.isMaterialYou} || returning $it)"
}

@Composable
fun DynamicTheme(
    option: ThemeOption = ThemeOption.THEME_DEFAULT,
    isDarkTheme: Boolean = shouldDisplayDarkTheme(),
    isDynamicColor: Boolean = shouldDisplayAsMaterialYou(),
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