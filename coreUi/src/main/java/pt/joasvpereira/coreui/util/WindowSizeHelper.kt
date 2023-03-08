package pt.joasvpereira.coreui.util

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.joasvpereira.loggger.extentions.logThis

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

    fun currentWidthSize(): WidthSize = when {
        widthSizeClass == WindowWidthSizeClass.Expanded -> WidthSize.Expanded
        widthSizeClass == WindowWidthSizeClass.Medium -> WidthSize.Medium
        else -> WidthSize.Compact
    }
}
