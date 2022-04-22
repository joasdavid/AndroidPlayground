package pt.joasvpereira.xorganizer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val green_light_primary = Color(0xFF146d22)
val green_light_onPrimary = Color(0xFFffffff)
val green_light_primaryContainer = Color(0xFF9ff79a)
val green_light_onPrimaryContainer = Color(0xFF002203)
val green_light_secondary = Color(0xFF625B71)
val green_light_onSecondary = Color(0xFFFFFFFF)
val green_light_secondaryContainer = Color(0xFFE8DEF8)
val green_light_onSecondaryContainer = Color(0xFF1D192B)
val green_light_tertiary = Color(0xFF6e5e00)
val green_light_onTertiary = Color(0xFFffffff)
val green_light_tertiaryContainer = Color(0xFFfce365)
val green_light_onTertiaryContainer = Color(0xFF211b00)
val green_light_error = Color(0xFFB3261E)
val green_light_errorContainer = Color(0xFFF9DEDC)
val green_light_onError = Color(0xFFFFFFFF)
val green_light_onErrorContainer = Color(0xFF410E0B)
val green_light_background = Color(0xFFfdfdf5)
val green_light_onBackground = Color(0xFF1a1c18)
val green_light_surface = Color(0xFFfdfdf5)
val green_light_onSurface = Color(0xFF1a1c18)
val green_light_surfaceVariant = Color(0xFFE7E0EC)
val green_light_onSurfaceVariant = Color(0xFF49454F)
val green_light_outline = Color(0xFF79747E)
val green_light_inverseOnSurface = Color(0xFFf1f1ea)
val green_light_inverseSurface = Color(0xFF2f312c)
val green_light_inversePrimary = Color(0xFF84da81)
val green_light_shadow = Color(0xFF000000)

val green_dark_primary = Color(0xFF84da81)
val green_dark_onPrimary = Color(0xFF003907)
val green_dark_primaryContainer = Color(0xFF00530f)
val green_dark_onPrimaryContainer = Color(0xFF9ff79a)
val green_dark_secondary = Color(0xFFCCC2DC)
val green_dark_onSecondary = Color(0xFF332D41)
val green_dark_secondaryContainer = Color(0xFF4A4458)
val green_dark_onSecondaryContainer = Color(0xFFE8DEF8)
val green_dark_tertiary = Color(0xFFdec74d)
val green_dark_onTertiary = Color(0xFF393000)
val green_dark_tertiaryContainer = Color(0xFF534600)
val green_dark_onTertiaryContainer = Color(0xFFfce365)
val green_dark_error = Color(0xFFF2B8B5)
val green_dark_errorContainer = Color(0xFF8C1D18)
val green_dark_onError = Color(0xFF601410)
val green_dark_onErrorContainer = Color(0xFFF9DEDC)
val green_dark_background = Color(0xFF1a1c18)
val green_dark_onBackground = Color(0xFFe3e3dc)
val green_dark_surface = Color(0xFF1a1c18)
val green_dark_onSurface = Color(0xFFe3e3dc)
val green_dark_surfaceVariant = Color(0xFF49454F)
val green_dark_onSurfaceVariant = Color(0xFFCAC4D0)
val green_dark_outline = Color(0xFF938F99)
val green_dark_inverseOnSurface = Color(0xFF1a1c18)
val green_dark_inverseSurface = Color(0xFFe3e3dc)
val green_dark_inversePrimary = Color(0xFF146d22)
val green_dark_shadow = Color(0xFF000000)

private val LightGreenColorPalette = lightColorScheme(
    primary = green_light_primary,
    onPrimary = green_light_onPrimary,
    primaryContainer = green_light_primaryContainer,
    onPrimaryContainer = green_light_onPrimaryContainer,
    inversePrimary = green_light_inversePrimary,
    secondary = green_light_secondary,
    onSecondary = green_light_onSecondary,
    secondaryContainer = green_light_secondaryContainer,
    onSecondaryContainer = green_light_onSecondaryContainer,
    tertiary = green_light_tertiary,
    onTertiary = green_light_onTertiary,
    tertiaryContainer = green_light_tertiaryContainer,
    onTertiaryContainer = green_light_onTertiaryContainer,
    background = green_light_background,
    onBackground = green_light_onBackground,
    surface = green_light_surface,
    onSurface = green_light_onSurface,
    surfaceVariant = green_light_surfaceVariant,
    onSurfaceVariant = green_light_onSurfaceVariant,
    surfaceTint = green_light_onSurface,
    inverseSurface = green_light_inverseSurface,
    inverseOnSurface = green_light_inverseOnSurface,
    error = green_light_error,
    onError = green_light_onError,
    errorContainer = green_light_errorContainer,
    onErrorContainer = green_light_onErrorContainer,
    outline = green_light_outline,
)

private val DarkGreenColorPalette = darkColorScheme(
    primary = green_dark_primary,
    onPrimary = green_dark_onPrimary,
    primaryContainer = green_dark_primaryContainer,
    onPrimaryContainer = green_dark_onPrimaryContainer,
    inversePrimary = green_dark_inversePrimary,
    secondary = green_dark_secondary,
    onSecondary = green_dark_onSecondary,
    secondaryContainer = green_dark_secondaryContainer,
    onSecondaryContainer = green_dark_onSecondaryContainer,
    tertiary = green_dark_tertiary,
    onTertiary = green_dark_onTertiary,
    tertiaryContainer = green_dark_tertiaryContainer,
    onTertiaryContainer = green_dark_onTertiaryContainer,
    background = green_dark_background,
    onBackground = green_dark_onBackground,
    surface = green_dark_surface,
    onSurface = green_dark_onSurface,
    surfaceVariant = green_dark_surfaceVariant,
    onSurfaceVariant = green_dark_onSurfaceVariant,
    surfaceTint = green_dark_onSurface,
    inverseSurface = green_dark_inverseSurface,
    inverseOnSurface = green_dark_inverseOnSurface,
    error = green_dark_error,
    onError = green_dark_onError,
    errorContainer = green_dark_errorContainer,
    onErrorContainer = green_dark_onErrorContainer,
    outline = green_dark_outline,
)

@Composable
fun GreenTheme(darkTheme: Boolean = isSystemInDarkTheme(),
        isToApplyToSystemUi: Boolean = false, content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkGreenColorPalette
    } else {
        LightGreenColorPalette
    }

    isToApplyToSystemUi.IfTrue {
        SetupSystemColor(
            color = MaterialTheme.colorScheme.background
        )
    }

    androidx.compose.material3.MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        //shapes = Shapes,
        content = content
        //content =  content
    )
}
