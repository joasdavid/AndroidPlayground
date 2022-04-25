package pt.joasvpereira.xorganizer.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.ThemeColorsIndicator

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
    options: List<T>,
    previewContent: @Composable (T?) -> Unit,
    selectedOption: T? = options.firstOrNull(),
    onSelectedOptionChanges: (T) -> Unit,
    expandableContent: @Composable (T) -> Unit
) {
    var parentSize by remember { mutableStateOf(Size.Zero) }
    Row(
        Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.extraSmall
            )
            .clickable {
                onSelectionOpenStateChanges(!selectionOpenState)
            }
            .then(modifier)
            .onGloballyPositioned {
                parentSize = it.size.toSize()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(Modifier.padding(8.dp)) {
            Box(Modifier.weight(1f)) {
                previewContent(selectedOption)
            }
            if (dropdownIcon != null) DropdownIconPlacer(dropdownIcon = dropdownIcon)
        }
        DropdownMenu(
            modifier = Modifier.width(with(LocalDensity.current){
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
}

data class DropdownIcon(val vecImg: ImageVector, val contentDescription: String, val space: Dp)

@Composable
fun DropdownIconPlacer(dropdownIcon: DropdownIcon) {
    Icon(
        modifier = Modifier.padding(start = dropdownIcon.space),
        imageVector = dropdownIcon.vecImg,
        contentDescription = dropdownIcon.contentDescription
    )
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
            previewContent = {
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
            onSelectedOptionChanges = {}
        )
        SimpleSpace(size = 10.dp)
        DropdownSelector<Any>(
            selectionOpenState = open,
            onSelectionOpenStateChanges = {
                open = !open
            },
            options = listOf(),
            previewContent = {
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
            options = listOf(),
            previewContent = {
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
            options = listOf(),
            previewContent = {
                Text(text = "this is a theme")
            },
            expandableContent = {

            },
            onSelectedOptionChanges = {}, dropdownIcon = null, selectedOption = null
        )
    }
}