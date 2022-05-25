package pt.joasvpereira.xorganizer.compose.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.min

data class CircleChartItem(
    val percentage: Float,
    val color: Color
)

@Composable
fun CircleChart2(
    items: List<CircleChartItem>,
    backgroundColor: Color = MaterialTheme.colorScheme.outline,
    radius: Dp = 70.dp,
    strokeSize: Dp = 20.dp,
    innerPadding: Dp = 16.dp,
    delay: Int = 0
) {
    /**
     * use case 1 :
     * 2 = .5f
     * 2 = .5f
     *
     * use case 2 :
     * 1 = .25f
     * 3 = .75f
     *
     * use case 3 :
     * 3 = .75f
     * 1 = .25f
     */


    val newList = mutableListOf<CircleChartItem>()
    //val minIndex = items.minByOrNull { it.percentage }
    //val max = items.maxByOrNull { it.percentage }?.percentage ?: 0f
    //val diff = 1f - max
    //items.forEachIndexed { index, _ ->
    //    if(index != minIndex ?: -1)
    //    newList.add(items[index].copy(percentage = items[index].percentage + diff))
    //}

    CircleChart(
        items = newList,
        backgroundColor = backgroundColor,
        radius = radius,
        strokeSize = strokeSize,
        innerPadding = innerPadding,
        delay = delay
    )
}

@Composable
fun CircleChart(
    items: List<CircleChartItem>,
    backgroundColor: Color = MaterialTheme.colorScheme.outline,
    radius: Dp = 70.dp,
    strokeSize: Dp = 20.dp,
    innerPadding: Dp = 16.dp,
    delay: Int = 0
) {
    var animationPlayed by remember { mutableStateOf(false) }
    val progressValue: Float by animateFloatAsState(
        targetValue = if (animationPlayed) 360f else 0f,
        animationSpec = tween(durationMillis = 3000, delayMillis = delay)
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Canvas(
        modifier = Modifier
            .padding(innerPadding)
            .size(radius * 2)
    ) {
        drawArc(
            color = backgroundColor,
            startAngle = -90f,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(width = strokeSize.toPx())
        )

        items.sortedByDescending { it.percentage }.forEach {
            drawArc(
                color = it.color,
                startAngle = -90f,
                sweepAngle = min(360f * it.percentage, progressValue),
                useCenter = false,
                style = Stroke(
                    width = strokeSize.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
    }
}

@Preview()
@Composable
fun CircleChartPreview() {
    CircleChart(
        items = listOf(
            CircleChartItem(
                .5f,
                Color.Red
            ),
            CircleChartItem(
                .3f,
                Color.Green
            ),
            CircleChartItem(
                .1f,
                Color.Yellow
            )
        )
    )
}