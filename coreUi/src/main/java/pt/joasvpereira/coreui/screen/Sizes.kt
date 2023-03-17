package pt.joasvpereira.coreui.screen

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import org.jetbrains.annotations.TestOnly
import pt.joasvpereira.coreui.util.WindowSizeHelper

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun LoadWindowSize(activity: Activity) {
    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    WindowSizeHelper.load(
        windowSizeClass = calculateWindowSizeClass(activity = activity),
        dpHeight = screenHeight,
        dpWidth = screenWidth,
    )
}

@TestOnly
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun LoadWindowSize(size: DpSize) {
    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    WindowSizeHelper.load(
        windowSizeClass = WindowSizeClass.calculateFromSize(size),
        dpHeight = screenHeight,
        dpWidth = screenWidth,
    )
}

@Composable
fun getActivityWindowHeight(): WindowSizeHelper.HeightSize {
    return WindowSizeHelper.currentHeightSize()
}

@Composable
fun getActivityWindowWidth(): WindowSizeHelper.WidthSize {
    return WindowSizeHelper.currentWidthSize()
}
