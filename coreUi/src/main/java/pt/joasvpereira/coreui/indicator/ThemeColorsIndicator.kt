package pt.joasvpereira.coreui.indicator

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import pt.joasvpereira.coreui.preview.ThemesProvider
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.theme.ThemeOption

@Composable
fun ThemeColorsIndicator(
    themeOption: ThemeOption = ThemeOption.THEME_DEFAULT,
    size: Dp = 48.dp,
    borderStroke: BorderStroke = BorderStroke(width = 1.5.dp, color = Color.White),
) {
    DynamicTheme(themeOption) {
        Box {
            Row(
                modifier = Modifier
                    .size(size)
                    .border(
                        border = borderStroke,
                        shape = CircleShape,
                    ),
            ) {
                SecondaryIndicator()
                TertiaryIndicator()
            }
            VerticalLine(borderStroke, size)
            PrimaryIndicator(size, borderStroke)
            HorizontalLine(size, borderStroke)
        }
    }
}

private const val INDICATOR_MAX_WIDTH_FRACTION_50 = .5f
private const val ROTATION_OF_90_DEGREES = 90f

@Composable
private fun PrimaryIndicator(size: Dp, borderStroke: BorderStroke) {
    Row(
        modifier = Modifier
            .size(size)
            .border(
                border = borderStroke,
                shape = CircleShape,
            )
            .rotate(ROTATION_OF_90_DEGREES),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(INDICATOR_MAX_WIDTH_FRACTION_50)
                .fillMaxHeight()
                .weight(1f)
                .background(
                    color = Color.Transparent,
                    shape = semiCircleRightShape,
                ),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(INDICATOR_MAX_WIDTH_FRACTION_50)
                .fillMaxHeight()
                .weight(1f)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = semiCircleLeftShape,
                ),
        )
    }
}

@Composable
private fun RowScope.SecondaryIndicator() {
    Box(
        modifier = Modifier
            .fillMaxWidth(INDICATOR_MAX_WIDTH_FRACTION_50)
            .fillMaxHeight()
            .weight(1f)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = semiCircleRightShape,
            ),
    )
}

@Composable
private fun RowScope.TertiaryIndicator() {
    Box(
        modifier = Modifier
            .fillMaxWidth(INDICATOR_MAX_WIDTH_FRACTION_50)
            .fillMaxHeight()
            .weight(1f)
            .background(
                color = MaterialTheme.colorScheme.tertiaryContainer,
                shape = semiCircleLeftShape,
            ),
    )
}

@Composable
private fun BoxScope.VerticalLine(borderStroke: BorderStroke, size: Dp) {
    Box(
        modifier = Modifier
            .width(borderStroke.width)
            .height(size)
            .background(borderStroke.brush)
            .align(Alignment.Center),
    )
}

@Composable
private fun BoxScope.HorizontalLine(size: Dp, borderStroke: BorderStroke) {
    Box(
        modifier = Modifier
            .width(size)
            .height(1.dp)
            .background(borderStroke.brush)
            .align(Alignment.Center),
    )
}

val semiCircleRightShape = GenericShape { size, _ ->
    arcTo(
        rect = Rect(center = Offset(x = size.width, y = size.height / 2), size.width),
        startAngleDegrees = 90f,
        sweepAngleDegrees = 180f,
        false,
    )
}

val semiCircleLeftShape = GenericShape { size, _ ->
    arcTo(
        rect = Rect(center = Offset(x = 0f, y = size.height / 2), size.width),
        startAngleDegrees = -90f,
        sweepAngleDegrees = 180f,
        false,
    )
}

@UiModePreview
@Composable
private fun ThemeColorsIndicatorPreview(@PreviewParameter(ThemesProvider::class) theme: ThemeOption) {
    DynamicTheme() {
        ThemeColorsIndicator(themeOption = theme)
    }
}
