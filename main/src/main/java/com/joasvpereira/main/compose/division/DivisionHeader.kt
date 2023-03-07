package com.joasvpereira.main.compose.division

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.joasvpereira.lib.compose.spacer.SimpleSpace
import com.joasvpereira.main.presentation.icons.DivisionIcons
import pt.joasvpereira.coreui.theme.DynamicTheme
import kotlin.math.min

@Composable
fun DivisionHeader(
    shieldImg: Int,
    nrFolders: Int,
    nrItems: Int,
    modifier: Modifier,
    shouldDisplayWithoutAnimation: Boolean = false,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SimpleSpace(size = 20.dp)
        val total = nrItems.toFloat() + nrFolders.toFloat()
        DivisionChart(
            nrFolders,
            if (total > 0) 1f else 0f,
            nrItems,
            nrItems.div(total),
            shieldImg,
            modifier = Modifier,
            shouldDisplayWithoutAnimation = shouldDisplayWithoutAnimation,
        )
        SimpleSpace(size = 20.dp)
    }
}

@Composable
fun DivisionChart(
    nrFolders: Int,
    percentageFolders: Float,
    nrItems: Int,
    percentageItems: Float,
    shieldImg: Int,
    modifier: Modifier,
    shouldDisplayWithoutAnimation: Boolean = false,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(contentAlignment = Alignment.Center) {
            CircleChart(
                items = buildChartItems(percentageFolders, percentageItems),
                backgroundColor = MaterialTheme.colorScheme.outline.copy(alpha = ALPHA_50),
                radius = 25.dp,
                strokeSize = 10.dp,
                innerPadding = 0.dp,
                delay = 1000,
                shouldDisplayWithoutAnimation = shouldDisplayWithoutAnimation,
            )
            Icon(
                painter = painterResource(id = shieldImg),
                contentDescription = null,
                modifier = Modifier.size(25.dp),
            )
        }
        SimpleSpace(size = 20.dp)
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(15.dp)
                        .background(MaterialTheme.colorScheme.tertiaryContainer, CircleShape),
                )
                SimpleSpace(size = 5.dp)
                Text(text = "Boxes: $nrFolders")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(15.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
                )
                SimpleSpace(size = 5.dp)
                Text(text = "Items: $nrItems")
            }
        }
    }
}

@Composable
fun buildChartItems(
    percentageFolders: Float,
    percentageItems: Float,
): List<CircleChartItem> {
    return listOf(
        CircleChartItem(percentageFolders, MaterialTheme.colorScheme.tertiaryContainer),
        CircleChartItem(percentageItems, MaterialTheme.colorScheme.primaryContainer),
    )
}

data class CircleChartItem(
    val percentage: Float,
    val color: Color,
)

@Composable
fun CircleChart(
    items: List<CircleChartItem>,
    backgroundColor: Color = MaterialTheme.colorScheme.outline,
    radius: Dp = 70.dp,
    strokeSize: Dp = 20.dp,
    innerPadding: Dp = 16.dp,
    delay: Int = 0,
    shouldDisplayWithoutAnimation: Boolean = false,
) {
    var animationPlayed by remember { mutableStateOf(shouldDisplayWithoutAnimation) }
    val progressValue: Float by animateFloatAsState(
        targetValue = if (animationPlayed) CHART_ANIM_END_VALUE else CHART_ANIM_START_VALUE,
        animationSpec = tween(durationMillis = CHART_ANIM_DURATION, delayMillis = delay),
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Canvas(
        modifier = Modifier
            .padding(innerPadding)
            .size(radius * 2),
    ) {
        drawArc(
            color = backgroundColor,
            startAngle = CHART_START_ANGLE,
            sweepAngle = CHART_SWEEP_ANGLE,
            useCenter = false,
            style = Stroke(width = strokeSize.toPx()),
        )

        items.sortedByDescending { it.percentage }.forEach {
            drawArc(
                color = it.color,
                startAngle = CHART_START_ANGLE,
                sweepAngle = min(CHART_SWEEP_ANGLE * it.percentage, progressValue),
                useCenter = false,
                style = Stroke(
                    width = strokeSize.toPx(),
                ),
            )
        }
    }
}

const val ALPHA_50 = .5f
const val CHART_ANIM_START_VALUE = 0f
const val CHART_ANIM_END_VALUE = 360f
const val CHART_ANIM_DURATION = 3000
const val CHART_START_ANGLE = -90f
const val CHART_SWEEP_ANGLE = 360f

@Suppress("all")
@Preview
@Composable
private fun CircleChartPreview() {
    DynamicTheme {
        Box(modifier = Modifier.size(60.dp), contentAlignment = Alignment.Center) {
            CircleChart(
                items = buildChartItems(1f, .25f),
                backgroundColor = MaterialTheme.colorScheme.outline.copy(alpha = .5f),
                radius = 25.dp,
                strokeSize = 10.dp,
                innerPadding = 0.dp,
                delay = 0,
                shouldDisplayWithoutAnimation = true,
            )
        }
    }
}

@Suppress("all")
@Preview
@Composable
fun DivisionChartPreview() {
    DynamicTheme {
        DivisionChart(
            nrFolders = 3,
            percentageFolders = 1f,
            nrItems = 1,
            percentageItems = .25f,
            shieldImg = DivisionIcons.cactus.resId,
            modifier = Modifier,
            shouldDisplayWithoutAnimation = true,
        )
    }
}

@Preview
@Composable
private fun DivisionHeaderPreview() {
    DynamicTheme {
        DivisionHeader(
            shieldImg = DivisionIcons.cactus.resId,
            nrFolders = 9,
            nrItems = 49,
            modifier = Modifier,
            shouldDisplayWithoutAnimation = true,
        )
    }
}
