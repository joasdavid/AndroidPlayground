package com.joasvpereira.main.compose.division.list.elements

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import com.joasvpereira.main.compose.division.BaseDivisionItemContainer
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.ThemeOption
import pt.joasvpereira.coreui.preview.ThemesProvider
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.shield.NameShield

@Composable
fun ObjectItem(
    name: String,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
) {
    BaseDivisionItemContainer(
        onClick = onClick,
        onDeleteClick = onDeleteClick,
        onEditClick = onEditClick,
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NameShield(
                text = name,
                modifier = Modifier.size(45.dp),
                backgroundColor = MaterialTheme.colorScheme.onPrimaryContainer,
                textColor = MaterialTheme.colorScheme.primaryContainer,
                borderSize = 0.dp
            )
            SimpleSpace(size = 8.dp)
            Text(text = name)
        }
    }
}

@Preview(group = "Single")
@Composable
private fun ObjectItemPreview() {
    DynamicTheme() {
        ObjectItem(name = "test", onClick = {}, onDeleteClick = {}, onEditClick = {}) 
    }
}

@UiModePreview
@Composable
private fun ObjectItemThemedPreview(@PreviewParameter(ThemesProvider::class) theme: ThemeOption) {
    DynamicTheme(theme) {
        ObjectItem(name = "HDMI Cable", onClick = {}, onDeleteClick = {}, onEditClick = {}, )
    }
}