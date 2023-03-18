package pt.joasvpereira.coreui.adaptative

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.joasvpereira.lib.compose.spacer.VerticalSpace
import pt.joasvpereira.coreui.screen.getActivityWindowHeight
import pt.joasvpereira.coreui.util.WindowSizeHelper

@Composable
fun AdaptableHeaderSpace() {
    val topSpace = when (getActivityWindowHeight()) {
        WindowSizeHelper.HeightSize.Compact,
        WindowSizeHelper.HeightSize.Medium,
        -> 20.dp

        WindowSizeHelper.HeightSize.Large,
        WindowSizeHelper.HeightSize.Expanded,
        -> 100.dp
    }
    VerticalSpace(height = topSpace)
}
