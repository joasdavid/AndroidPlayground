package pt.joasvpereira.coreui.dragble

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import pt.joasvpereira.coreui.DynamicTheme
import kotlin.math.roundToInt
import com.joasvpereira.loggger.Logger
import com.joasvpereira.loggger.extentions.logThis

class DraggableToRevelState(
    var offsetXInit: Float = 0f,
) {

    /**
     * @Composable
    fun rememberDraggableState(onDelta: (Float) -> Unit): DraggableState {
    val onDeltaState = rememberUpdatedState(onDelta)
    return remember { DraggableState { onDeltaState.value.invoke(it) } }
    }

     */
    private var offsetX = mutableStateOf(offsetXInit)
    private val onDelta: (Float) -> Unit = {
        val finalValue = offsetX.value + it
        finalValue.logThis {
            """
                LOG INFO
                finalValue = $finalValue
                offsetX.value = ${offsetX.value}
                it = $it
                maxGap = $maxGap
            """.trimIndent()
        }
        offsetX.value = if (finalValue <= maxGap) finalValue else offsetX.value
    }
    private var onDeltaState = mutableStateOf(onDelta)
    var draggableState by mutableStateOf(DraggableState { onDeltaState.value.invoke(it) })

    fun provideIntOffset() = run { IntOffset(offsetX.value.roundToInt(), 0) }

    var maxGap by mutableStateOf(359f)

    fun close() {
        offsetX.value = 0f
    }
}

@Composable
fun rememberDraggableToRevelState(
    offsetX: Float = 0f,
): DraggableToRevelState {
    return DraggableToRevelState(
            offsetXInit = offsetX
        )
}

@Composable
fun DraggableToRevel(
    modifier: Modifier = Modifier,
    draggableToRevelState: DraggableToRevelState = rememberDraggableToRevelState(),
    contentBehind: @Composable BoxScope.()-> Unit,
    content: @Composable ()-> Unit,
) {
    // Get local density from composable
    val localDensity = LocalDensity.current

    // Create element height in pixel state
    var columnHeightPx by remember {
        mutableStateOf(0f)
    }
    val maxHeight =  with(localDensity) { columnHeightPx.toDp() }
    Box() {
        maxHeight.takeIf { it > 0.dp }?.let {height ->
            Box(modifier = modifier.height(height)
                .onGloballyPositioned { coordinates ->
                    // Set column height using the LayoutCoordinates
                    draggableToRevelState.maxGap = coordinates.size.width.toFloat()
                }
            ) {
                contentBehind()
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            //.offset { IntOffset(offsetX.roundToInt(), 0) }
            .offset { draggableToRevelState.provideIntOffset() }
            .draggable(state = draggableToRevelState.draggableState, orientation = Orientation.Horizontal)
            .onGloballyPositioned { coordinates ->
                // Set column height using the LayoutCoordinates
                columnHeightPx = coordinates.size.height.toFloat()
            }.then(modifier)
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun DraggableToRevelPreview() {
    DynamicTheme() {
        Column {
            val draggableToRevelState =  rememberDraggableToRevelState(offsetX = 0f)
            DraggableToRevel(
                modifier = Modifier.clip(RoundedCornerShape(50.dp)),
                draggableToRevelState = draggableToRevelState,
                contentBehind = {
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .width(100.dp)
                        .background(Color.Red)) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                    }
                }
            ) {
                Box(modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth()
                    .background(Color.Green)
                    .clickable { draggableToRevelState.close() }
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
                        }
                    ),
                text = "Drag me!"
            )
        }
    }
}