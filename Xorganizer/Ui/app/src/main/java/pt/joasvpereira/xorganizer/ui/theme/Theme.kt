package pt.joasvpereira.xorganizer.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

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
private val LightBlueColorPalette = lightColors(
    primary = Cyan500,
    primaryVariant = Cyan700,
    surface = Cyan700,
    secondary = Cyan200,
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
    when(option) {
        ThemeOption.THEME_PURPLE -> PurpleTheme(content = content)
        ThemeOption.THEME_BLUE -> BlueTheme(content = content)
        ThemeOption.THEME_GREEN -> GreenTheme(content = content)
        else -> BaseTheme(content = content)
    }
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
    val colors = if (darkTheme) {
        LightBlueColorPalette
    } else {
        LightBlueColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}