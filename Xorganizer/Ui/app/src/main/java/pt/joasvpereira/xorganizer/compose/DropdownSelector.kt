package pt.joasvpereira.xorganizer.compose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.ThemeColorsIndicator

enum class DropdownSelectorStates {
    ON_FOCUS,
    OUT_FOCUS,
    ERROR,
    DISABLE
}

private fun Modifier.border(parameters: StyleParameters) =
    this.border(
        width = parameters.width,
        color = parameters.color,
        shape = parameters.shape
    )

@Composable
fun defaultBorderStyle(): StyleConfiguration = StyleConfiguration(
    unFocus = StyleParameters(
        width = 1.dp,
        color = MaterialTheme.colorScheme.outline,
        shape = MaterialTheme.shapes.extraSmall
    ),
    focus = StyleParameters(
        width = 2.dp,
        color = MaterialTheme.colorScheme.primary,
        shape = MaterialTheme.shapes.extraSmall
    ),
    error = StyleParameters(
        width = 2.dp,
        color = MaterialTheme.colorScheme.error,
        shape = MaterialTheme.shapes.extraSmall
    ),
    disable = StyleParameters(
        width = 1.dp,
        color = MaterialTheme.colorScheme.outline,
        shape = MaterialTheme.shapes.extraSmall
    ),
)

data class StyleConfiguration(
    val unFocus: StyleParameters,
    val focus: StyleParameters,
    val error: StyleParameters,
    val disable: StyleParameters,
)

data class StyleParameters(
    val width: Dp, val color: Color, val shape: Shape
)

data class DropdownIcon(
    val vecImg: ImageVector,
    val contentDescription: String,
    val space: Dp,
    val tint: Color? = null
)

@Composable
fun <T> DropdownSelector(
    modifier: Modifier = Modifier,
    selectionOpenState: Boolean,
    onSelectionOpenStateChanges: (Boolean) -> Unit,
    dropdownIcon: DropdownIcon? = DropdownIcon(
        vecImg = Icons.Filled.ArrowDropDown,
        contentDescription = "Arrow Down",
        space = 5.dp
    ),
    styleConfiguration: StyleConfiguration = defaultBorderStyle(),
    isError: Boolean = false,
    isDisabled: Boolean = false,
    options: List<T>,
    previewContent: @Composable (T?, DropdownSelectorStates) -> Unit,
    selectedOption: T? = options.firstOrNull(),
    onSelectedOptionChanges: (T) -> Unit,
    expandableContent: @Composable (T) -> Unit
) {
    val borderParameters = when {
        isError -> styleConfiguration.error
        isDisabled -> styleConfiguration.disable.copy(
            color = styleConfiguration.disable.color.copy(alpha = ContentAlpha.disabled)
        )
        selectionOpenState -> styleConfiguration.focus
        else -> styleConfiguration.unFocus
    }
    val currentState = when {
        isDisabled -> DropdownSelectorStates.DISABLE
        isError -> DropdownSelectorStates.ERROR
        selectionOpenState -> DropdownSelectorStates.ON_FOCUS
        else -> DropdownSelectorStates.OUT_FOCUS
    }

    DropdownContainer(
        borderParameters,
        currentState,
        onSelectionOpenStateChanges,
        selectionOpenState,
        modifier,
        previewContent,
        selectedOption,
        dropdownIcon,
        styleConfiguration,
        options,
        expandableContent,
        onSelectedOptionChanges
    )
}

@Composable
private fun <T> DropdownContainer(
    borderParameters: StyleParameters,
    currentState: DropdownSelectorStates,
    onSelectionOpenStateChanges: (Boolean) -> Unit,
    selectionOpenState: Boolean,
    modifier: Modifier,
    previewContent: @Composable (T?, DropdownSelectorStates) -> Unit,
    selectedOption: T?,
    dropdownIcon: DropdownIcon?,
    styleConfiguration: StyleConfiguration,
    options: List<T>,
    expandableContent: @Composable (T) -> Unit,
    onSelectedOptionChanges: (T) -> Unit
) {
    var parentSize by remember { mutableStateOf(Size.Zero) }
    Row(
        Modifier
            .border(borderParameters)
            .clickable {
                if (currentState != DropdownSelectorStates.DISABLE)
                    onSelectionOpenStateChanges(!selectionOpenState)
            }
            .then(modifier)
            .onGloballyPositioned {
                parentSize = it.size.toSize()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        DropdownContent<T>(
            previewContent,
            selectedOption,
            currentState,
            dropdownIcon,
            styleConfiguration
        )
        ExpandableMenu(
            parentSize,
            selectionOpenState,
            onSelectionOpenStateChanges,
            options,
            expandableContent,
            onSelectedOptionChanges
        )
    }
}

@Composable
private fun <T> DropdownContent(
    previewContent: @Composable (T?, DropdownSelectorStates) -> Unit,
    selectedOption: T?,
    currentState: DropdownSelectorStates,
    dropdownIcon: DropdownIcon?,
    styleConfiguration: StyleConfiguration
) {
    Row(Modifier.padding(8.dp)) {
        Box(Modifier.weight(1f)) {
            previewContent(selectedOption, currentState)
        }
        if (dropdownIcon != null) DropdownIconPlacer(
            dropdownIcon = dropdownIcon,
            styleConfiguration = styleConfiguration,
            state = currentState
        )
    }
}

@Composable
fun DropdownIconPlacer(
    dropdownIcon: DropdownIcon,
    styleConfiguration: StyleConfiguration,
    state: DropdownSelectorStates
) {
    val tint = when (state) {
        DropdownSelectorStates.ERROR -> styleConfiguration.error.color
        DropdownSelectorStates.DISABLE -> styleConfiguration.disable.color.copy(alpha = ContentAlpha.disabled)
        else -> dropdownIcon.tint
    }
    val angle: Float by animateFloatAsState(targetValue = if (state == DropdownSelectorStates.ON_FOCUS) 180f else 0f)

    Icon(
        modifier = Modifier
            .padding(start = dropdownIcon.space)
            .rotate(angle),
        imageVector = dropdownIcon.vecImg,
        contentDescription = dropdownIcon.contentDescription,
        tint = tint ?: LocalContentColor.current,
    )
}

@Composable
private fun <T> ExpandableMenu(
    parentSize: Size,
    selectionOpenState: Boolean,
    onSelectionOpenStateChanges: (Boolean) -> Unit,
    options: List<T>,
    expandableContent: @Composable (T) -> Unit,
    onSelectedOptionChanges: (T) -> Unit
) {
    DropdownMenu(
        modifier = Modifier.width(with(LocalDensity.current) {
            parentSize.width.toDp()
        }),
        expanded = selectionOpenState,
        onDismissRequest = {
            onSelectionOpenStateChanges(false)
        }
    ) {
        options.forEach {
            DropdownMenuItem(text = { expandableContent(it) }, onClick = {
                onSelectedOptionChanges(it)
                onSelectionOpenStateChanges(!selectionOpenState)
            })
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0x000)
@Composable
fun DropdownSelectorPreview() {
    var open by remember { mutableStateOf(false) }
    Column {
        DropdownSelector<Any>(
            selectionOpenState = open,
            onSelectionOpenStateChanges = {
                open = !open
            },
            options = listOf(),
            previewContent = { _, _ ->
                Row {
                    ThemeColorsIndicator(size = 24.dp)
                    SimpleSpace(size = 5.dp)
                    Text(text = "this is a theme")
                    SimpleSpace(size = 5.dp)
                    Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
                }
            },
            expandableContent = {

            },
            onSelectedOptionChanges = {},
            isError = true
        )
        SimpleSpace(size = 10.dp)
        DropdownSelector<Any>(
            selectionOpenState = open,
            onSelectionOpenStateChanges = {
                open = !open
            },
            options = listOf(),
            previewContent = { _, _ ->
                ThemeColorsIndicator(size = 24.dp)
                SimpleSpace(size = 5.dp)
                Text(text = "this is a theme")
            },
            expandableContent = {

            },
            onSelectedOptionChanges = {}
        )
        SimpleSpace(size = 10.dp)
        DropdownSelector<Any>(
            selectionOpenState = open,
            onSelectionOpenStateChanges = {
                open = !open
            },
            isDisabled = true,
            options = listOf(),
            previewContent = { _, state ->
                if (state != DropdownSelectorStates.DISABLE)
                    Text(text = "this is a theme")
            },
            expandableContent = {

            },
            onSelectedOptionChanges = {}
        )
        SimpleSpace(size = 10.dp)
        DropdownSelector<Any>(
            selectionOpenState = open,
            isDisabled = true,
            onSelectionOpenStateChanges = {
                open = !open
            },
            options = listOf(),
            previewContent = { _, _ ->
                Text(
                    text = "this is a theme",
                    color = Color.Unspecified.copy(alpha = ContentAlpha.disabled)
                )
            },
            expandableContent = {

            },
            onSelectedOptionChanges = {}, dropdownIcon = null, selectedOption = null
        )
    }
}