package com.joasvpereira.main.compose.division

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import kotlinx.coroutines.launch
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.theme.ThemeOption
import pt.joasvpereira.coreui.dragble.DraggableToRevel
import pt.joasvpereira.coreui.dragble.DraggableToRevelState
import pt.joasvpereira.coreui.dragble.rememberDraggableToRevelState

@Composable
internal fun BaseDivisionItemContainer(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primaryContainer,
    onClick : () -> Unit,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val state = rememberDraggableToRevelState(
        direction = DraggableToRevelState.RevelDirection.RIGHT
    )
    DraggableToRevel(
        modifier = modifier
            .clickable { onClick() },
        draggableToRevelState = state,
        contentBehindColor = color.copy(alpha = .45f),
        contentBehind = {
            ContentBehind(
                state = state,
                onDeleteClick = onDeleteClick,
                onEditClick = onEditClick
            )
        },
        shape = RoundedCornerShape(15.dp)
    ) {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
            color = color,
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                content()
                DragIndicator()
            }
        }
    }
}

@Composable
private fun BoxScope.DragIndicator() {
    Spacer(modifier = Modifier
        .padding(end = 10.dp)
        .width(1.5.dp)
        .height(12.dp)
        .background(MaterialTheme.colorScheme.outline, shape = RoundedCornerShape(100))
        .align(Alignment.CenterEnd)
    )
    Spacer(modifier = Modifier
        .padding(end = 15.dp)
        .width(1.5.dp)
        .height(24.dp)
        .background(MaterialTheme.colorScheme.outline, shape = RoundedCornerShape(100))
        .align(Alignment.CenterEnd)
    )
}

@Composable()
private fun RowScope.ContentBehind(
    state: DraggableToRevelState,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    SimpleSpace(size = 15.dp)
    Box(modifier = Modifier
        .aspectRatio(1f)
        .clickable {
            coroutineScope.launch {
                state.close()
                onDeleteClick()
            }
        },
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
    }
    Box(modifier = Modifier
        .aspectRatio(1f)
        .clickable {
            coroutineScope.launch {
                state.close()
                onEditClick()
            }
        },
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = Icons.Default.Edit, contentDescription = null)
    }
    SimpleSpace(size = 15.dp)
}

@Preview(backgroundColor = 1L)
@Composable
private fun BaseDivisionItemContainerPreview() {
    DynamicTheme(ThemeOption.THEME_GREEN, isDarkTheme = true) {
        BaseDivisionItemContainer(Modifier.padding(10.dp), onClick = {}, onDeleteClick = {}, onEditClick = {}) {
            SimpleSpace(size = 66.dp)
            Text("This is a test")
            Text(text = "\n\n\nsdadsfs", color = MaterialTheme.colorScheme.onPrimaryContainer)
        }
    }
}
@Preview(backgroundColor = 1L)
@Composable
private fun ContentBehindPreview() {
    DynamicTheme(ThemeOption.THEME_GREEN, isDarkTheme = true) {
        Row(modifier = Modifier.height(48.dp)) {
            ContentBehind(
                state = DraggableToRevelState(0f, direction = DraggableToRevelState.RevelDirection.LEFT),
                onDeleteClick = { },
                onEditClick = {}
            )
        }
    }
}