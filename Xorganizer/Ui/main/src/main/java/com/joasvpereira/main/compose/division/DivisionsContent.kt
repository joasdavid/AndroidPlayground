package com.joasvpereira.main.compose.division

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import com.joasvpereira.main.compose.common.container.element.ElementAction
import com.joasvpereira.main.compose.common.container.element.ElementsContainer
import com.joasvpereira.main.compose.division.list.elements.BoxItem
import com.joasvpereira.main.compose.division.list.elements.ObjectItem
import com.joasvpereira.main.domain.data.DivisionElement
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.ThemeOption
import pt.joasvpereira.coreui.preview.ThemesProvider
import pt.joasvpereira.coreui.preview.UiModePreview

@Composable
fun DivisionContent(
    listItem: List<DivisionElement>,
    listBottomPadding: Dp = 0.dp,
    onClick: (DivisionElement, ElementAction) -> Unit
) {
    ElementsContainer(
        listItem = listItem,
        onClick = onClick,
        listBottomPadding = listBottomPadding,
        emptyText = "It seems like there are no items or boxes in your account at the moment, but don't worry! You can add them anytime by selecting the 'Add' option and entering the necessary details.",
    )
}

private val previewData = mutableListOf<DivisionElement>().apply {
    (1..40).forEach {
        if (it % 2 == 0) {
            add(DivisionElement.Item(name = "name $it", id = it))
        } else {
            add(DivisionElement.Box(name = "name $it", id = it))
        }
    }
}

@Preview(group = "Single")
@Composable
private fun DivisionContentPreview() {
    DynamicTheme {
        DivisionContent(previewData, onClick = { _, _ -> })
    }
}

@Preview(group = "SingleEmpty")
@Composable
private fun DivisionContentPreview_empty() {
    DynamicTheme {
        DivisionContent(listOf(), onClick = { _, _ -> })
    }
}

@UiModePreview
@Composable
private fun DivisionContentThemedPreview(
    @PreviewParameter(ThemesProvider::class) theme: ThemeOption,
) {
    DynamicTheme(theme) {
        DivisionContent(previewData, onClick = { _, _ -> })
    }
}