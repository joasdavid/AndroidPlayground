package com.joasvpereira.main.compose.division

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.ThemeOption
import pt.joasvpereira.coreui.box.BoxImage
import pt.joasvpereira.coreui.preview.ThemesProvider
import pt.joasvpereira.coreui.preview.UiModePreview

@Composable
fun BoxItem(
    name: String,
    onClick: () -> Unit
) {
    ItemContainer(
        onClick = onClick,
        color = MaterialTheme.colorScheme.tertiaryContainer
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BoxImage(tint = MaterialTheme.colorScheme.onTertiaryContainer)
            SimpleSpace(size = 8.dp)
            Text(text = name)
        }
    }
}

@Preview(group = "Single")
@Composable
private fun BoxItemPreview() {
    DynamicTheme() {
        BoxItem("Box nr one") {}
    }
}

@UiModePreview
@Composable
private fun BoxItemThemedPreview(@PreviewParameter(ThemesProvider::class) theme: ThemeOption) {
    DynamicTheme(theme) {
        BoxItem("Box nr one") {}
    }
}