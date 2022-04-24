package pt.joasvpereira.xorganizer.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val blue_light_primary = Color(0xFF434ed1)
val blue_light_onPrimary = Color(0xFFffffff)
val blue_light_primaryContainer = Color(0xFFdfe0ff)
val blue_light_onPrimaryContainer = Color(0xFF00006f)
val blue_light_secondary = Color(0xFF625B71)
val blue_light_onSecondary = Color(0xFFFFFFFF)
val blue_light_secondaryContainer = Color(0xFFE8DEF8)
val blue_light_onSecondaryContainer = Color(0xFF1D192B)
val blue_light_tertiary = Color(0xFF6650a4)
val blue_light_onTertiary = Color(0xFFffffff)
val blue_light_tertiaryContainer = Color(0xFFe9ddff)
val blue_light_onTertiaryContainer = Color(0xFF22005e)
val blue_light_error = Color(0xFFB3261E)
val blue_light_errorContainer = Color(0xFFF9DEDC)
val blue_light_onError = Color(0xFFFFFFFF)
val blue_light_onErrorContainer = Color(0xFF410E0B)
val blue_light_background = Color(0xFFFFFBFE)
val blue_light_onBackground = Color(0xFF1C1B1F)
val blue_light_surface = Color(0xFFFFFBFE)
val blue_light_onSurface = Color(0xFF1C1B1F)
val blue_light_surfaceVariant = Color(0xFFE7E0EC)
val blue_light_onSurfaceVariant = Color(0xFF49454F)
val blue_light_outline = Color(0xFF79747E)
val blue_light_inverseOnSurface = Color(0xFFF4EFF4)
val blue_light_inverseSurface = Color(0xFF313033)
val blue_light_inversePrimary = Color(0xFFbcc2ff)
val blue_light_shadow = Color(0xFF000000)

val blue_dark_primary = Color(0xFFbcc2ff)
val blue_dark_onPrimary = Color(0xFF030da4)
val blue_dark_primaryContainer = Color(0xFF2832b8)
val blue_dark_onPrimaryContainer = Color(0xFFdfe0ff)
val blue_dark_secondary = Color(0xFFCCC2DC)
val blue_dark_onSecondary = Color(0xFF332D41)
val blue_dark_secondaryContainer = Color(0xFF4A4458)
val blue_dark_onSecondaryContainer = Color(0xFFE8DEF8)
val blue_dark_tertiary = Color(0xFFd0bcff)
val blue_dark_onTertiary = Color(0xFF371e73)
val blue_dark_tertiaryContainer = Color(0xFF4e378b)
val blue_dark_onTertiaryContainer = Color(0xFFe9ddff)
val blue_dark_error = Color(0xFFF2B8B5)
val blue_dark_errorContainer = Color(0xFF8C1D18)
val blue_dark_onError = Color(0xFF601410)
val blue_dark_onErrorContainer = Color(0xFFF9DEDC)
val blue_dark_background = Color(0xFF1C1B1F)
val blue_dark_onBackground = Color(0xFFE6E1E5)
val blue_dark_surface = Color(0xFF1C1B1F)
val blue_dark_onSurface = Color(0xFFE6E1E5)
val blue_dark_surfaceVariant = Color(0xFF49454F)
val blue_dark_onSurfaceVariant = Color(0xFFCAC4D0)
val blue_dark_outline = Color(0xFF938F99)
val blue_dark_inverseOnSurface = Color(0xFF1C1B1F)
val blue_dark_inverseSurface = Color(0xFFE6E1E5)
val blue_dark_inversePrimary = Color(0xFF434ed1)
val blue_dark_shadow = Color(0xFF000000)

private val LightBlueColorPalette = lightColorScheme(
    primary = blue_light_primary,
    onPrimary = blue_light_onPrimary,
    primaryContainer = blue_light_primaryContainer,
    onPrimaryContainer = blue_light_onPrimaryContainer,
    inversePrimary = blue_light_inversePrimary,
    secondary = blue_light_secondary,
    onSecondary = blue_light_onSecondary,
    secondaryContainer = blue_light_secondaryContainer,
    onSecondaryContainer = blue_light_onSecondaryContainer,
    tertiary = blue_light_tertiary,
    onTertiary = blue_light_onTertiary,
    tertiaryContainer = blue_light_tertiaryContainer,
    onTertiaryContainer = blue_light_onTertiaryContainer,
    background = blue_light_background,
    onBackground = blue_light_onBackground,
    surface = blue_light_surface,
    onSurface = blue_light_onSurface,
    surfaceVariant = blue_light_surfaceVariant,
    onSurfaceVariant = blue_light_onSurfaceVariant,
    surfaceTint = blue_light_onSurface,
    inverseSurface = blue_light_inverseSurface,
    inverseOnSurface = blue_light_inverseOnSurface,
    error = blue_light_error,
    onError = blue_light_onError,
    errorContainer = blue_light_errorContainer,
    onErrorContainer = blue_light_onErrorContainer,
    outline = blue_light_outline,
)

private val DarkBlueColorPalette = darkColorScheme(
    primary = blue_dark_primary,
    onPrimary = blue_dark_onPrimary,
    primaryContainer = blue_dark_primaryContainer,
    onPrimaryContainer = blue_dark_onPrimaryContainer,
    inversePrimary = blue_dark_inversePrimary,
    secondary = blue_dark_secondary,
    onSecondary = blue_dark_onSecondary,
    secondaryContainer = blue_dark_secondaryContainer,
    onSecondaryContainer = blue_dark_onSecondaryContainer,
    tertiary = blue_dark_tertiary,
    onTertiary = blue_dark_onTertiary,
    tertiaryContainer = blue_dark_tertiaryContainer,
    onTertiaryContainer = blue_dark_onTertiaryContainer,
    background = blue_dark_background,
    onBackground = blue_dark_onBackground,
    surface = blue_dark_surface,
    onSurface = blue_dark_onSurface,
    surfaceVariant = blue_dark_surfaceVariant,
    onSurfaceVariant = blue_dark_onSurfaceVariant,
    surfaceTint = blue_dark_onSurface,
    inverseSurface = blue_dark_inverseSurface,
    inverseOnSurface = blue_dark_inverseOnSurface,
    error = blue_dark_error,
    onError = blue_dark_onError,
    errorContainer = blue_dark_errorContainer,
    onErrorContainer = blue_dark_onErrorContainer,
    outline = blue_dark_outline,
)

@Composable
fun blueTheme(darkTheme: Boolean,): Theme {
    return Theme(
        color = if (darkTheme) {
            DarkBlueColorPalette
        } else {
            LightBlueColorPalette
        }
    )
}