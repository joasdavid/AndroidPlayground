package pt.joasvpereira.coreui.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import pt.joasvpereira.coreui.util.WindowSizeHelper

@Composable
fun defaultPadding(): PaddingValues = when (WindowSizeHelper.currentWidthSize()) {
    WindowSizeHelper.WidthSize.Compact,
    WindowSizeHelper.WidthSize.Medium,
    -> PaddingValues(horizontal = 20.dp)

    WindowSizeHelper.WidthSize.Expanded -> PaddingValues(horizontal = 60.dp, vertical = 20.dp)
}
