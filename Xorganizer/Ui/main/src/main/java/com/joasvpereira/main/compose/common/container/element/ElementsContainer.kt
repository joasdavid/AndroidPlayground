package com.joasvpereira.main.compose.common.container.element

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
import com.joasvpereira.main.compose.division.list.elements.BoxItem
import com.joasvpereira.main.compose.division.list.elements.ObjectItem
import com.joasvpereira.main.domain.data.DivisionElement
import com.joasvpereira.main.domain.data.Element
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.ThemeOption
import pt.joasvpereira.coreui.preview.ThemesProvider
import pt.joasvpereira.coreui.preview.UiModePreview

sealed interface ElementAction {
    object Open : ElementAction
    object Edit : ElementAction
    object Delete : ElementAction
}

@Composable
fun ElementsContainer(
    listItem: List<DivisionElement>,
    listBottomPadding: Dp = 0.dp,
    emptyText: String = "",
    onClick: (DivisionElement, ElementAction) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
    ) {
        Box(modifier = Modifier.padding(horizontal = 20.dp)) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                item { SimpleSpace(size = 40.dp) }
                if (listItem.isEmpty()) {
                    item {
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Text(
                                text = emptyText,
                                modifier = Modifier.fillMaxWidth(.5f),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                items(listItem) {
                    if (it is DivisionElement.Item) {
                        ObjectItem(
                            name = it.name,
                            onClick = { onClick(it, ElementAction.Open) },
                            onDeleteClick = { onClick(it, ElementAction.Delete) },
                            onEditClick = { onClick(it, ElementAction.Edit) }
                        )
                    }

                    if (it is DivisionElement.Box) {
                        BoxItem(name = it.name,
                            onClick = { onClick(it, ElementAction.Open) },
                            onDeleteClick = { onClick(it, ElementAction.Delete) },
                            onEditClick = { onClick(it, ElementAction.Edit) }
                        )
                    }
                    SimpleSpace(size = 10.dp)
                }

                item {
                    SimpleSpace(size = 70.dp)
                    SimpleSpace(size = listBottomPadding)
                }
            }
        }
    }
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
private fun ElementsConteinerPreview() {
    DynamicTheme {
        ElementsContainer(previewData, onClick = { _, _ -> })
    }
}

@Preview(group = "SingleEmpty")
@Composable
private fun ElementsConteinerPreview_empty() {
    DynamicTheme {
        ElementsContainer(listOf(), onClick = { _, _ -> })
    }
}

@UiModePreview
@Composable
private fun ElementsConteinerThemedPreview(
    @PreviewParameter(ThemesProvider::class) theme: ThemeOption,
) {
    DynamicTheme(theme) {
        ElementsContainer(previewData, onClick = { _, _ -> })
    }
}