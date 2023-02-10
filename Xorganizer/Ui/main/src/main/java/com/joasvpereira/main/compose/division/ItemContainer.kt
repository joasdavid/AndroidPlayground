package com.joasvpereira.main.compose.division

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.ThemeOption
import pt.joasvpereira.coreui.dragble.DraggableToRevel
import pt.joasvpereira.coreui.dragble.DraggableToRevelState
import pt.joasvpereira.coreui.dragble.rememberDraggableToRevelState

@Composable
fun ItemContainer(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primaryContainer,
    onClick : () -> Unit,
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
            Box(modifier = Modifier
                .aspectRatio(1f)
                .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
            Box(modifier = Modifier
                .aspectRatio(1f)
                .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }
            SimpleSpace(size = 15.dp)
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
        }
    }
}

@Preview(backgroundColor = 1L)
@Composable
private fun ItemContainerPreview() {
    DynamicTheme(ThemeOption.THEME_GREEN, isDarkTheme = true) {
        ItemContainer(Modifier.padding(10.dp), onClick = {}) {
            SimpleSpace(size = 66.dp)
            Text("This is a test")
            Text(text = "\n\n\nsdadsfs", color = MaterialTheme.colorScheme.onPrimaryContainer)
        }
    }
}