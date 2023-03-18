package pt.joasvpereira.sessionfeature.compose.select.session

import pt.joasvpereira.coreui.util.WindowSizeHelper

object SelectSessionScreenDefaults {
    private const val TWO_COLUMN = 2
    private const val THREE_COLUMN = 3

    @Suppress("all")
    fun numberOfColumns(): Int = when (WindowSizeHelper.currentWidthSize()) {
        WindowSizeHelper.WidthSize.Compact -> TWO_COLUMN
        WindowSizeHelper.WidthSize.Medium -> THREE_COLUMN
        WindowSizeHelper.WidthSize.Expanded -> TWO_COLUMN
    }

    fun expandedByScreenSize(): Boolean = when (WindowSizeHelper.currentWidthSize()) {
        WindowSizeHelper.WidthSize.Expanded -> true
        else -> false
    }
}
