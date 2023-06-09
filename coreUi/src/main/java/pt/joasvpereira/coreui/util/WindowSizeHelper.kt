package pt.joasvpereira.coreui.util

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.joasvpereira.loggger.extentions.logThis
import org.jetbrains.annotations.TestOnly

object WindowSizeHelper {
    private var widthSizeClass: WindowWidthSizeClass? = null

    private var heightSizeClass: WindowHeightSizeClass? = null

    private var dpHeight: Dp? = null
    private var dpWidth: Dp? = null

    fun load(
        windowSizeClass: WindowSizeClass,
        dpHeight: Dp,
        dpWidth: Dp,
    ) {
        this.widthSizeClass = windowSizeClass.widthSizeClass.logThis("ScreenSize")
        this.heightSizeClass = windowSizeClass.heightSizeClass.logThis("ScreenSize")
        this.dpWidth = dpWidth.logThis("ScreenSize")
        this.dpHeight = dpHeight.logThis("ScreenSize")
    }

    @TestOnly
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    fun load(
        dpHeight: Dp,
        dpWidth: Dp,
    ) {
        val sizeClass = WindowSizeClass.calculateFromSize(DpSize(dpWidth, dpHeight))
        this.widthSizeClass = sizeClass.widthSizeClass
        this.heightSizeClass = sizeClass.heightSizeClass
        this.dpWidth = dpWidth.logThis("ScreenSize")
        this.dpHeight = dpHeight.logThis("ScreenSize")
    }

    sealed interface HeightSize {
        object Compact : HeightSize
        object Medium : HeightSize
        object Large : HeightSize
        object Expanded : HeightSize
    }

    fun currentHeightSize(): HeightSize = when {
        heightSizeClass == WindowHeightSizeClass.Expanded -> HeightSize.Expanded
        heightSizeClass == WindowHeightSizeClass.Medium -> calcDiffMediumOrLarge()
        else -> HeightSize.Compact
    }

    private fun calcDiffMediumOrLarge(): HeightSize {
        return dpHeight?.run {
            return if (value >= 750.dp.value) {
                HeightSize.Large
            } else {
                HeightSize.Medium
            }
        } ?: HeightSize.Medium
    }

    sealed interface WidthSize {
        object Compact : WidthSize
        object Medium : WidthSize
        object Expanded : WidthSize
    }

    fun currentWidthSize(): WidthSize = when (widthSizeClass) {
        WindowWidthSizeClass.Expanded -> WidthSize.Expanded
        WindowWidthSizeClass.Medium -> WidthSize.Medium
        else -> WidthSize.Compact
    }

    val isWidthCompact: Boolean
        get() = currentWidthSize() == WidthSize.Compact
    val isWidthMedium: Boolean
        get() = currentWidthSize() == WidthSize.Medium
    val isWidthExpanded: Boolean
        get() = currentWidthSize() == WidthSize.Expanded

    val isHeightCompact: Boolean
        get() = currentHeightSize() == HeightSize.Compact
    val isHeightMedium: Boolean
        get() = currentHeightSize() == HeightSize.Medium
    val isHeightExpanded: Boolean
        get() = currentHeightSize() == HeightSize.Expanded
}
