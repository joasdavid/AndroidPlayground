package com.joasvpereira.main.compose.division.list.elements

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
import com.joasvpereira.lib.compose.spacer.SimpleSpace
import com.joasvpereira.main.compose.division.BaseDivisionItemContainer
import pt.joasvpereira.coreui.box.BoxImage
import pt.joasvpereira.coreui.preview.ThemesProvider
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.theme.ThemeOption

@Composable
fun BoxItem(
    name: String,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
) {
    BaseDivisionItemContainer(
        onClick = onClick,
        color = MaterialTheme.colorScheme.tertiaryContainer,
        onDeleteClick = onDeleteClick,
        onEditClick = onEditClick,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
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
    DynamicTheme {
        BoxItem("Box nr one", onClick = {}, onDeleteClick = {}, onEditClick = {})
    }
}

@UiModePreview
@Composable
private fun BoxItemThemedPreview(@PreviewParameter(ThemesProvider::class) theme: ThemeOption) {
    DynamicTheme(theme) {
        BoxItem("Box nr one", onClick = {}, onDeleteClick = {}, onEditClick = {})
    }
}
