@file:Suppress("all")

package pt.joasvpereira.coreui.dragble

import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.animateTo
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.joasvpereira.lib.compose.spacer.SimpleSpace
import com.joasvpereira.loggger.extentions.logThis
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pt.joasvpereira.coreui.theme.DynamicTheme
import kotlin.math.abs
import kotlin.math.roundToInt

class DraggableToRevelState(
    var offsetXInit: Float = 0f,
    val direction: RevelDirection,
) {

    enum class RevelDirection {
        LEFT,
        RIGHT,
    }

    private fun <T : Number> valueBetween(
        currentValue: T,
        max: T,
        min: T,
    ): T {
        if (currentValue.toDouble() >= min.toDouble() && currentValue.toDouble() <= max.toDouble()) return currentValue
        if (currentValue.toDouble() < min.toDouble()) return min
        return max
    }

    internal var isDragging: Boolean by mutableStateOf(false)
    private var offsetX = mutableStateOf(offsetXInit)
    private val onDelta: (Float) -> Unit = {
        val finalValue = offsetX.value + it
        offsetX.value = valueBetween(
            currentValue = finalValue,
            max = if (direction == RevelDirection.RIGHT) 0f else maxGap,
            min = if (direction == RevelDirection.RIGHT) 0 - maxGap else 0f,
        )
    }
    private var onDeltaState = mutableStateOf(onDelta)
    var draggableState by mutableStateOf(DraggableState { onDeltaState.value.invoke(it) })
    fun provideIntOffset() = run { IntOffset(offsetX.value.roundToInt(), 0) }

    var maxGap by mutableStateOf(0f)

    suspend fun endPosition() {
        if (abs(offsetX.value) > maxGap / 3) {
            open()
        } else {
            close()
        }
    }

    suspend fun toggle() {
        if (offsetX.value == 0f) {
            open()
        } else {
            close()
        }
    }

    suspend fun close() {
        val anim = AnimationState(offsetX.value)
        anim.animateTo(0f) {
            offsetX.value = this.value
        }
    }

    suspend fun open() {
        val anim = AnimationState(offsetX.value)
        anim.animateTo(if (direction == RevelDirection.LEFT) maxGap else 0 - maxGap) {
            offsetX.value = this.value
        }
    }
}

@Composable
fun rememberDraggableToRevelState(
    offsetX: Float = 0f,
    direction: DraggableToRevelState.RevelDirection = DraggableToRevelState.RevelDirection.LEFT,
): DraggableToRevelState {
    return DraggableToRevelState(
        offsetXInit = offsetX,
        direction = direction,
    )
}

@Composable
fun DraggableToRevel(
    modifier: Modifier = Modifier,
    draggableToRevelState: DraggableToRevelState = rememberDraggableToRevelState(),
    contentBehindColor: Color = MaterialTheme.colorScheme.surface,
    shape: Shape = RoundedCornerShape(0.dp),
    contentBehind: @Composable RowScope.() -> Unit,
    content: @Composable () -> Unit,
) {
    // Get local density from composable
    val localDensity = LocalDensity.current

    // Create element height in pixel state
    var columnHeightPx by remember {
        mutableStateOf(0f)
    }
    val maxHeight = with(localDensity) { columnHeightPx.toDp() }
    Box {
        maxHeight.takeIf { it > 0.dp }?.let { height ->
            Surface(
                modifier = modifier.clip(shape),
                color = contentBehindColor,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Row(
                        modifier = Modifier
                            .height(height)
                            // .background(Color.Green.copy(alpha = .25f))
                            .onGloballyPositioned { coordinates ->
                                // Set column height using the LayoutCoordinates
                                draggableToRevelState.maxGap = coordinates.size.width.toFloat()
                            }
                            .align(
                                if (draggableToRevelState.direction == DraggableToRevelState.RevelDirection.RIGHT) {
                                    Alignment.CenterEnd
                                } else {
                                    Alignment.CenterStart
                                },
                            ),
                    ) {
                        contentBehind()
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                // .offset { IntOffset(offsetX.roundToInt(), 0) }
                .offset { draggableToRevelState.provideIntOffset() }
                .draggable(
                    state = draggableToRevelState.draggableState,
                    orientation = Orientation.Horizontal,
                    onDragStarted = {
                        draggableToRevelState.isDragging = true
                        "START DRAGGING".logThis(tag = "MotionEvent")
                    },
                    onDragStopped = {
                        draggableToRevelState.isDragging = false
                        draggableToRevelState.endPosition()
                        "STOP DRAGGING".logThis(tag = "MotionEvent")
                    },
                )
                .onGloballyPositioned { coordinates ->
                    // Set column height using the LayoutCoordinates
                    columnHeightPx = coordinates.size.height.toFloat()
                }
                .clip(shape)
                .then(modifier),
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun DraggableToRevelPreview() {
    DynamicTheme {
        Column {
            val draggableToRevelState = rememberDraggableToRevelState(offsetX = 0f)
            DraggableToRevel(
                shape = RoundedCornerShape(50.dp),
                draggableToRevelState = draggableToRevelState,
                contentBehind = {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(100.dp)
                            .background(Color.Red),
                    ) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(100.dp)
                            .background(Color.Yellow),
                    ) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                    }
                },
            ) {
                val scopo: CoroutineScope = rememberCoroutineScope()
                Box(
                    modifier = Modifier
                        .height(70.dp)
                        .fillMaxWidth()
                        .background(Color.Green)
                        .clickable {
                            scopo.launch {
                                draggableToRevelState.toggle()
                            }
                        },
                ) {
                    Text(text = "qwertyuiop")
                }
            }

            SimpleSpace(size = 100.dp)

            var offsetX by remember { mutableStateOf(0f) }
            Text(
                modifier = Modifier
                    .offset { IntOffset(offsetX.roundToInt(), 0) }
                    .draggable(
                        orientation = Orientation.Horizontal,
                        state = rememberDraggableState { delta ->
                            offsetX += delta
                        },
                    ),
                text = "Drag me!",
            )
        }
    }
}
