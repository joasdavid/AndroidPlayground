package com.joasvpereira.main.compose.division

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.ThemeOption

@Composable
fun ItemContainer(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primaryContainer,
    onClick : () -> Unit,
    content: @Composable () -> Unit
) {
    Surface(modifier = modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(15.dp))
        .clickable { onClick() },
        color = color,
    ) {
        content()
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