package pt.joasvpereira.xorganizer.ui.theme

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val JetchatLightColorScheme = lightColorScheme(
    primary = Blue40,
    onPrimary = Color.White,
    primaryContainer = Blue90,
    onPrimaryContainer = Blue10,
    inversePrimary = Blue80,
    secondary = DarkBlue40,
    onSecondary = Color.White,
    secondaryContainer = DarkBlue90,
    onSecondaryContainer = DarkBlue10,
    tertiary = Yellow40,
    onTertiary = Color.White,
    tertiaryContainer = Yellow90,
    onTertiaryContainer = Yellow10,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = Grey99,
    onBackground = Grey10,
    surface = Grey99,
    onSurface = Grey10,
    inverseSurface = Grey20,
    inverseOnSurface = Grey95,
    surfaceVariant = BlueGrey90,
    onSurfaceVariant = BlueGrey30,
    outline = BlueGrey50
)


private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    surface = Purple700,
    secondary = Purple200,
    onSecondary = Color.Black,
    onSurface = Color.White

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@SuppressLint("ConflictingOnColor")
private val LightBlueColorPalette = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
)

@SuppressLint("ConflictingOnColor")
private val LightGreenColorPalette = lightColors(
    primary = LightGreen500,
    primaryVariant = LightGreen700,
    surface = LightGreen700,
    secondary = LightGreen200,
    onSurface = Color.White

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

enum class ThemeOption {
    THEME_DEFAULT,
    THEME_PURPLE,
    THEME_BLUE,
    THEME_GREEN;
}

@Composable
fun DynamicTheme(
    option: ThemeOption = ThemeOption.THEME_DEFAULT,
    content: @Composable() () -> Unit
) {
    //BlueTheme(content = content)
    JetchatTheme(content = content)
    //when(option) {
    //    ThemeOption.THEME_PURPLE -> PurpleTheme(content = content)
    //    ThemeOption.THEME_BLUE -> BlueTheme(content = content)
    //    ThemeOption.THEME_GREEN -> GreenTheme(content = content)
    //    else -> BaseTheme(content = content)
    //}
}

@Composable
fun BaseTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun PurpleTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun GreenTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        LightGreenColorPalette
    } else {
        LightGreenColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun BlueTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    //val colors = if (darkTheme) {
    //    LightBlueColorPalette
    //} else {
    //    LightBlueColorPalette
    //}

    androidx.compose.material3.MaterialTheme(
        colorScheme = LightBlueColorPalette,
        //typography = Typography,
        //shapes = Shapes,
        content = content
    )
}

@SuppressLint("NewApi")
@Composable
fun JetchatTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    isDynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val dynamicColor = isDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val myColorScheme = when {
        dynamicColor && isDarkTheme -> {
            dynamicDarkColorScheme(LocalContext.current)
        }
        dynamicColor && !isDarkTheme -> {
            dynamicLightColorScheme(LocalContext.current)
        }
        else -> JetchatLightColorScheme
    }

    androidx.compose.material3.MaterialTheme(
        colorScheme = myColorScheme
    ) {
        // (M3): MaterialTheme doesn't provide LocalIndication, remove when it does
        val rippleIndication = rememberRipple()
        CompositionLocalProvider(
            LocalIndication provides rippleIndication,
            content = content
        )
    }
}