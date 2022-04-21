package pt.joasvpereira.xorganizer.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val default_light_primary = Color(0xFF8d4f00)
val default_light_onPrimary = Color(0xFFffffff)
val default_light_primaryContainer = Color(0xFFffdcbc)
val default_light_onPrimaryContainer = Color(0xFF2d1600)
val default_light_secondary = Color(0xFF745c00)
val default_light_onSecondary = Color(0xFFffffff)
val default_light_secondaryContainer = Color(0xFFffe07b)
val default_light_onSecondaryContainer = Color(0xFF231b00)
val default_light_tertiary = Color(0xFF9c4044)
val default_light_onTertiary = Color(0xFFffffff)
val default_light_tertiaryContainer = Color(0xFFffd9d9)
val default_light_onTertiaryContainer = Color(0xFF410008)
val default_light_error = Color(0xFFB3261E)
val default_light_errorContainer = Color(0xFFF9DEDC)
val default_light_onError = Color(0xFFFFFFFF)
val default_light_onErrorContainer = Color(0xFF410E0B)
val default_light_background = Color(0xFFFFFBFE)
val default_light_onBackground = Color(0xFF1C1B1F)
val default_light_surface = Color(0xFFFFFBFE)
val default_light_onSurface = Color(0xFF1C1B1F)
val default_light_surfaceVariant = Color(0xFFE7E0EC)
val default_light_onSurfaceVariant = Color(0xFF49454F)
val default_light_outline = Color(0xFF79747E)
val default_light_inverseOnSurface = Color(0xFFF4EFF4)
val default_light_inverseSurface = Color(0xFF313033)
val default_light_inversePrimary = Color(0xFFffb871)
val default_light_shadow = Color(0xFF000000)

val default_dark_primary = Color(0xFFffb871)
val default_dark_onPrimary = Color(0xFF4b2700)
val default_dark_primaryContainer = Color(0xFF6c3b00)
val default_dark_onPrimaryContainer = Color(0xFFffdcbc)
val default_dark_secondary = Color(0xFFefc213)
val default_dark_onSecondary = Color(0xFF3d2f00)
val default_dark_secondaryContainer = Color(0xFF574400)
val default_dark_onSecondaryContainer = Color(0xFFffe07b)
val default_dark_tertiary = Color(0xFFffb2b3)
val default_dark_onTertiary = Color(0xFF60131b)
val default_dark_tertiaryContainer = Color(0xFF7d2a2f)
val default_dark_onTertiaryContainer = Color(0xFFffd9d9)
val default_dark_error = Color(0xFFF2B8B5)
val default_dark_errorContainer = Color(0xFF8C1D18)
val default_dark_onError = Color(0xFF601410)
val default_dark_onErrorContainer = Color(0xFFF9DEDC)
val default_dark_background = Color(0xFF1C1B1F)
val default_dark_onBackground = Color(0xFFE6E1E5)
val default_dark_surface = Color(0xFF1C1B1F)
val default_dark_onSurface = Color(0xFFE6E1E5)
val default_dark_surfaceVariant = Color(0xFF49454F)
val default_dark_onSurfaceVariant = Color(0xFFCAC4D0)
val default_dark_outline = Color(0xFF938F99)
val default_dark_inverseOnSurface = Color(0xFF1C1B1F)
val default_dark_inverseSurface = Color(0xFFE6E1E5)
val default_dark_inversePrimary = Color(0xFF8d4f00)
val default_dark_shadow = Color(0xFF000000)

private val LightDefaultColorPalette = lightColorScheme(
    primary = default_light_primary,
    onPrimary = default_light_onPrimary,
    primaryContainer = default_light_primaryContainer,
    onPrimaryContainer = default_light_onPrimaryContainer,
    inversePrimary = default_light_inversePrimary,
    secondary = default_light_secondary,
    onSecondary = default_light_onSecondary,
    secondaryContainer = default_light_secondaryContainer,
    onSecondaryContainer = default_light_onSecondaryContainer,
    tertiary = default_light_tertiary,
    onTertiary = default_light_onTertiary,
    tertiaryContainer = default_light_tertiaryContainer,
    onTertiaryContainer = default_light_onTertiaryContainer,
    background = default_light_background,
    onBackground = default_light_onBackground,
    surface = default_light_surface,
    onSurface = default_light_onSurface,
    surfaceVariant = default_light_surfaceVariant,
    onSurfaceVariant = default_light_onSurfaceVariant,
    surfaceTint = default_light_onSurface,
    inverseSurface = default_light_inverseSurface,
    inverseOnSurface = default_light_inverseOnSurface,
    error = default_light_error,
    onError = default_light_onError,
    errorContainer = default_light_errorContainer,
    onErrorContainer = default_light_onErrorContainer,
    outline = default_light_outline,
)

private val DarkDefaultColorPalette = darkColorScheme(
    primary = default_dark_primary,
    onPrimary = default_dark_onPrimary,
    primaryContainer = default_dark_primaryContainer,
    onPrimaryContainer = default_dark_onPrimaryContainer,
    inversePrimary = default_dark_inversePrimary,
    secondary = default_dark_secondary,
    onSecondary = default_dark_onSecondary,
    secondaryContainer = default_dark_secondaryContainer,
    onSecondaryContainer = default_dark_onSecondaryContainer,
    tertiary = default_dark_tertiary,
    onTertiary = default_dark_onTertiary,
    tertiaryContainer = default_dark_tertiaryContainer,
    onTertiaryContainer = default_dark_onTertiaryContainer,
    background = default_dark_background,
    onBackground = default_dark_onBackground,
    surface = default_dark_surface,
    onSurface = default_dark_onSurface,
    surfaceVariant = default_dark_surfaceVariant,
    onSurfaceVariant = default_dark_onSurfaceVariant,
    surfaceTint = default_dark_onSurface,
    inverseSurface = default_dark_inverseSurface,
    inverseOnSurface = default_dark_inverseOnSurface,
    error = default_dark_error,
    onError = default_dark_onError,
    errorContainer = default_dark_errorContainer,
    onErrorContainer = default_dark_onErrorContainer,
    outline = default_dark_outline,
)

@Composable
fun DefaultTheme(isDarkTheme: Boolean = isSystemInDarkTheme(),
                 isDynamicColor: Boolean = true,
                 content: @Composable () -> Unit
) {
    val dynamicColor = isDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colors = when {
        dynamicColor && isDarkTheme -> {
            dynamicDarkColorScheme(LocalContext.current)
        }
        dynamicColor && !isDarkTheme -> {
            dynamicLightColorScheme(LocalContext.current)
        }
        isDarkTheme -> DarkDefaultColorPalette
        else -> LightDefaultColorPalette
    }

    androidx.compose.material3.MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        //shapes = Shapes,
        content = { Surface(content = content) }
        //content =  content
    )
}
