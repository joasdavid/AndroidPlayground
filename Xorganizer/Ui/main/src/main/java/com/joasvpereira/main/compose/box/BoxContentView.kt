package com.joasvpereira.main.compose.box

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.joasvpereira.main.compose.division.DivisionContent
import com.joasvpereira.main.domain.data.DivisionElement
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.ThemeOption
import pt.joasvpereira.coreui.preview.ThemesProvider
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.scaffold.AppScaffold
import pt.joasvpereira.coreui.scaffold.ToolBarConfig

@Composable
fun BoxContentView(
    box: DivisionElement.Box,
    onBackClick: () -> Unit,
) {
    AppScaffold(
        toolBarConfig = ToolBarConfig(
         title = box.name,
         onLeftIconClick = onBackClick
        ),
        paddingValues = PaddingValues(0.dp),
    ) {
        DivisionContent(listItem = emptyList(),) { DivisionElement, DivisionsContentAction ->

        }
    }
}

@UiModePreview
@Composable
fun BoxContentViewPreview(@PreviewParameter(ThemesProvider::class) theme: ThemeOption) {
    DynamicTheme(theme) {
        BoxContentView(box = DivisionElement.Box(id = 1, name = "Box num one", description = ""), onBackClick = {})
    }
}