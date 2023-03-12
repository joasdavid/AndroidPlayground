package com.joasvpereira.main.compose.division

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.joasvpereira.main.compose.common.container.element.ElementAction
import com.joasvpereira.main.compose.common.container.element.ElementsContainer
import com.joasvpereira.main.domain.data.DivisionElement
import pt.joasvpereira.coreui.preview.ThemesProvider
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.theme.ThemeOption
import pt.joasvpereira.main.R

@Composable
fun DivisionContent(
    listItem: List<DivisionElement>,
    listBottomPadding: Dp = 0.dp,
    onClick: (DivisionElement, ElementAction) -> Unit,
) {
    ElementsContainer(
        listItem = listItem,
        onClick = onClick,
        listBottomPadding = listBottomPadding,
        emptyText = stringResource(R.string.division_without_elements_message),
    )
}

@Suppress("all")
private val previewData = mutableListOf<DivisionElement>().apply {
    (1..40).forEach {
        if (it % 2 == 0) {
            add(DivisionElement.Item(name = "Name $it", id = it))
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
