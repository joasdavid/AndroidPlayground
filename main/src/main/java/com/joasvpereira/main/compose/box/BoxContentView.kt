package com.joasvpereira.main.compose.box

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.joasvpereira.lib.compose.spacer.SimpleSpace
import com.joasvpereira.main.compose.common.container.element.ElementAction
import com.joasvpereira.main.compose.common.container.element.ElementsContainer
import com.joasvpereira.main.domain.data.DivisionElement
import pt.joasvpereira.coreui.preview.ThemesProvider
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.scaffold.AppScaffold
import pt.joasvpereira.coreui.scaffold.ToolBarConfig
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.theme.ThemeOption
import pt.joasvpereira.main.R

@Composable
fun BoxContentView(
    box: DivisionElement.Box,
    itemsList: List<DivisionElement.Item> = emptyList(),
    isLoading: Boolean = false,
    emptyText: String = "It looks like you don't have any items yet, but don't worry, you can easily add items by clicking the 'Add' button.",
    onClick: (divisionElement: DivisionElement, elementAction: ElementAction) -> Unit = { _, _ -> },
    onAddButtonClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    AppScaffold(
        toolBarConfig = ToolBarConfig(
            title = box.name,
            onLeftIconClick = onBackClick,
            horizontalPadding = 20.dp,
        ),
        isLoading = isLoading,
        paddingValues = PaddingValues(0.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            ElementsContainer(
                listItem = itemsList,
                listBottomPadding = it.calculateBottomPadding(),
                emptyText = emptyText,
                onClick = onClick,
            )
            FloatingActionButton(
                onClick = onAddButtonClick,
                modifier = Modifier
                    .padding(20.dp)
                    .padding(bottom = it.calculateBottomPadding())
                    .align(Alignment.BottomEnd),
                containerColor = MaterialTheme.colorScheme.primary,
            ) {
                Row(modifier = Modifier.padding(10.dp)) {
                    Icon(painter = painterResource(id = R.drawable.ic_item), contentDescription = null)
                    SimpleSpace(size = 10.dp)
                    Text(text = "Add Item")
                }
            }
        }
    }
}

@Suppress("all")
private val previewList = mutableListOf<DivisionElement.Item>().apply {
    (1..40).forEach {
        add(
            DivisionElement.Item(
                id = it,
                name = "Item $it",
                description = "",
            ),
        )
    }
}

@Preview(group = "Single")
@Composable
fun BoxContentViewPreview() {
    DynamicTheme {
        BoxContentView(
            box = DivisionElement.Box(id = 1, name = "Box num one", description = ""),
            itemsList = previewList,
            onBackClick = {},
            onAddButtonClick = { },
        )
    }
}

@Preview(group = "SingleEmpty")
@Composable
fun BoxContentViewPreview_empty() {
    DynamicTheme {
        BoxContentView(
            box = DivisionElement.Box(id = 1, name = "Box num one", description = ""),
            onBackClick = {},
            onAddButtonClick = { },
        )
    }
}

@UiModePreview
@Composable
fun BoxContentViewPreview(@PreviewParameter(ThemesProvider::class) theme: ThemeOption) {
    DynamicTheme(theme) {
        BoxContentView(
            box = DivisionElement.Box(
                id = 1,
                name = "Box num one",
                description = "",
            ),
            itemsList = previewList,
            onBackClick = {},
            onAddButtonClick = { },
        )
    }
}
