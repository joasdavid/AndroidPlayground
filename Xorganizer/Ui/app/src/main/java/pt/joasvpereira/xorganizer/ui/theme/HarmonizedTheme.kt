package com.example.compose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.android.material.color.ColorRoles
import com.google.android.material.color.MaterialColors
import pt.joasvpereira.xorganizer.ui.theme.AppTypography
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_background
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_error
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_errorContainer
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_inverseOnSurface
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_inversePrimary
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_inverseSurface
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_onBackground
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_onError
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_onErrorContainer
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_onPrimary
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_onPrimaryContainer
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_onSecondary
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_onSecondaryContainer
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_onSurface
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_onSurfaceVariant
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_onTertiary
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_onTertiaryContainer
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_outline
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_primary
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_primaryContainer
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_secondary
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_secondaryContainer
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_surface
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_surfaceVariant
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_tertiary
import pt.joasvpereira.xorganizer.ui.theme.blue_dark_tertiaryContainer
import pt.joasvpereira.xorganizer.ui.theme.blue_light_background
import pt.joasvpereira.xorganizer.ui.theme.blue_light_error
import pt.joasvpereira.xorganizer.ui.theme.blue_light_errorContainer
import pt.joasvpereira.xorganizer.ui.theme.blue_light_inverseOnSurface
import pt.joasvpereira.xorganizer.ui.theme.blue_light_inversePrimary
import pt.joasvpereira.xorganizer.ui.theme.blue_light_inverseSurface
import pt.joasvpereira.xorganizer.ui.theme.blue_light_onBackground
import pt.joasvpereira.xorganizer.ui.theme.blue_light_onError
import pt.joasvpereira.xorganizer.ui.theme.blue_light_onErrorContainer
import pt.joasvpereira.xorganizer.ui.theme.blue_light_onPrimary
import pt.joasvpereira.xorganizer.ui.theme.blue_light_onPrimaryContainer
import pt.joasvpereira.xorganizer.ui.theme.blue_light_onSecondary
import pt.joasvpereira.xorganizer.ui.theme.blue_light_onSecondaryContainer
import pt.joasvpereira.xorganizer.ui.theme.blue_light_onSurface
import pt.joasvpereira.xorganizer.ui.theme.blue_light_onSurfaceVariant
import pt.joasvpereira.xorganizer.ui.theme.blue_light_onTertiary
import pt.joasvpereira.xorganizer.ui.theme.blue_light_onTertiaryContainer
import pt.joasvpereira.xorganizer.ui.theme.blue_light_outline
import pt.joasvpereira.xorganizer.ui.theme.blue_light_primary
import pt.joasvpereira.xorganizer.ui.theme.blue_light_primaryContainer
import pt.joasvpereira.xorganizer.ui.theme.blue_light_secondary
import pt.joasvpereira.xorganizer.ui.theme.blue_light_secondaryContainer
import pt.joasvpereira.xorganizer.ui.theme.blue_light_surface
import pt.joasvpereira.xorganizer.ui.theme.blue_light_surfaceVariant
import pt.joasvpereira.xorganizer.ui.theme.blue_light_tertiary
import pt.joasvpereira.xorganizer.ui.theme.blue_light_tertiaryContainer

private val LightThemeColors = lightColorScheme(
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
private val DarkThemeColors = darkColorScheme(
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
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        LightThemeColors
    } else {
        DarkThemeColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        content = content
    )
}

data class CustomColor(val name:String, val color: Color, val harmonized: Boolean, var roles: ColorRoles)
data class ExtendedColors(val colors: Array<CustomColor>)

val initializeExtended = ExtendedColors(
    arrayOf(
    ))

val LocalExtendedColors = staticCompositionLocalOf {
    initializeExtended
}


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun HarmonizedTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    isDynamic: Boolean = true,
    content: @Composable() () -> Unit
) {
    val colors = if (isDynamic) {
        val context = LocalContext.current
        if (useDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else {
        if (useDarkTheme) DarkThemeColors else LightThemeColors
    }


        MaterialTheme(
            colorScheme = colors,
            typography = AppTypography,
            content = content
        )

}

object Extended {
}