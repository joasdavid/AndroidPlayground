@file:Suppress("MagicNumber")

package pt.joasvpereira.coreui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val green_light_primary = Color(0xFF346B23)
val green_light_onPrimary = Color(0xFFFFFFFF)
val green_light_primaryContainer = Color(0xFFB4F39A)
val green_light_onPrimaryContainer = Color(0xFF032100)
val green_light_secondary = Color(0xFF296B2A)
val green_light_onSecondary = Color(0xFFFFFFFF)
val green_light_secondaryContainer = Color(0xFFACF4A2)
val green_light_onSecondaryContainer = Color(0xFF002203)
val green_light_tertiary = Color(0xFF4F6602)
val green_light_onTertiary = Color(0xFFFFFFFF)
val green_light_tertiaryContainer = Color(0xFFD0EE82)
val green_light_onTertiaryContainer = Color(0xFF161F00)
val green_light_error = Color(0xFFBA1A1A)
val green_light_errorContainer = Color(0xFFFFDAD6)
val green_light_onError = Color(0xFFFFFFFF)
val green_light_onErrorContainer = Color(0xFF410002)
val green_light_background = Color(0xFFFDFDF6)
val green_light_onBackground = Color(0xFF1A1C18)
val green_light_surface = Color(0xFFFDFDF6)
val green_light_onSurface = Color(0xFF1A1C18)
val green_light_surfaceVariant = Color(0xFFDFE4D7)
val green_light_onSurfaceVariant = Color(0xFF43483F)
val green_light_outline = Color(0xFF73796E)
val green_light_inverseOnSurface = Color(0xFFF1F1EA)
val green_light_inverseSurface = Color(0xFF2F312D)
val green_light_inversePrimary = Color(0xFF99D681)
val green_light_shadow = Color(0xFF000000)
val green_light_surfaceTint = Color(0xFF346B23)
val green_light_outlineVariant = Color(0xFFC3C8BC)
val green_light_scrim = Color(0xFF000000)

val green_dark_primary = Color(0xFF99D681)
val green_dark_onPrimary = Color(0xFF093900)
val green_dark_primaryContainer = Color(0xFF1B520B)
val green_dark_onPrimaryContainer = Color(0xFFB4F39A)
val green_dark_secondary = Color(0xFF91D888)
val green_dark_onSecondary = Color(0xFF003908)
val green_dark_secondaryContainer = Color(0xFF0A5314)
val green_dark_onSecondaryContainer = Color(0xFFACF4A2)
val green_dark_tertiary = Color(0xFFB5D269)
val green_dark_onTertiary = Color(0xFF273500)
val green_dark_tertiaryContainer = Color(0xFF3A4D00)
val green_dark_onTertiaryContainer = Color(0xFFD0EE82)
val green_dark_error = Color(0xFFFFB4AB)
val green_dark_errorContainer = Color(0xFF93000A)
val green_dark_onError = Color(0xFF690005)
val green_dark_onErrorContainer = Color(0xFFFFDAD6)
val green_dark_background = Color(0xFF1A1C18)
val green_dark_onBackground = Color(0xFFE3E3DC)
val green_dark_surface = Color(0xFF1A1C18)
val green_dark_onSurface = Color(0xFFE3E3DC)
val green_dark_surfaceVariant = Color(0xFF43483F)
val green_dark_onSurfaceVariant = Color(0xFFC3C8BC)
val green_dark_outline = Color(0xFF8D9387)
val green_dark_inverseOnSurface = Color(0xFF1A1C18)
val green_dark_inverseSurface = Color(0xFFE3E3DC)
val green_dark_inversePrimary = Color(0xFF346B23)
val green_dark_shadow = Color(0xFF000000)
val green_dark_surfaceTint = Color(0xFF99D681)
val green_dark_outlineVariant = Color(0xFF43483F)
val green_dark_scrim = Color(0xFF000000)

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
fun greenTheme(darkTheme: Boolean): Theme {
    return Theme(
        color = if (darkTheme) {
            DarkGreenColorPalette
        } else {
            LightGreenColorPalette
        },
    )
}
