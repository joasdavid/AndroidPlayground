package com.joasvpereira.main.compose.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import com.joasvpereira.main.compose.common.popup.CreateItemPopup
import com.joasvpereira.main.compose.common.popup.CreateItemPopupStateHolder
import com.joasvpereira.main.domain.data.ItemDetail
import com.joasvpereira.main.presentation.icons.DivisionIcons
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.ThemeOption
import pt.joasvpereira.coreui.preview.ThemesProvider
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.scaffold.AppScaffold
import pt.joasvpereira.coreui.scaffold.ToolBarConfig

@Composable
fun ItemDetailView(
    details: ItemDetail,
    isLoading : Boolean = false,
    createPopupState: CreateItemPopupStateHolder = remember {
        CreateItemPopupStateHolder(isOnEditMode = true, isVisible = false)
    },
    onBackClick: () -> Unit
) {
    AnimatedVisibility(visible = createPopupState.isVisible) {
        CreateItemPopup(
            createPopupState
        )
    }

    AppScaffold(
        toolBarConfig = ToolBarConfig(title = details.name, onLeftIconClick = onBackClick),
        isTinted = details.parentDivision.themeOption != null,
        isLoading = isLoading
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {
                SimpleSpace(size = 80.dp)
            }
            item {
                GenericItemDetailsSection(
                    id = details.id,
                    name = details.name,
                    description = details.description,
                    onEditClick = { createPopupState.isVisible = true }
                )
            }
            item {
                SimpleSpace(size = 20.dp)
                ParentDetailsSection(division = details.parentDivision, box = details.parentBox)
            }
        }
    }
}

@Preview(group = "Single")
@Composable
private fun ItemDetailViewPreview() {
    DynamicTheme() {
        ItemDetailView(details = ItemDetail(
            id = 1,
            name = "Item XPTO",
            description = "",
            parentDivision = ItemDetail.ParentDivision(id = 1, name = "Hall", themeOption = ThemeOption.THEME_DEFAULT, divisionIcon = DivisionIcons.hanger),
            parentBox = ItemDetail.ParentBox(id = 1, name = "Shoe box")
        ), onBackClick = {}, isLoading = false)
    }
}

@Preview(group = "SingleEmpty")
@Composable
private fun ItemDetailViewPreview_empty() {
    DynamicTheme() {
        ItemDetailView(details = ItemDetail(
            id = 0,
            name = "",
            description = "",
            parentDivision = ItemDetail.ParentDivision(id = 0, name = "", themeOption = null, divisionIcon = null),
            parentBox = null
        ), onBackClick = {}, isLoading = true)
    }
}

@UiModePreview
@Composable
private fun ItemDetailViewPreview(@PreviewParameter(ThemesProvider::class) theme: ThemeOption) {
    DynamicTheme(theme) {
        ItemDetailView(details = ItemDetail(
            id = 1,
            name = "",
            description = "",
            parentDivision = ItemDetail.ParentDivision(id = 1, name = "Hall", themeOption = theme, divisionIcon = DivisionIcons.lampDesk),
            parentBox = ItemDetail.ParentBox(id = 1, name = "Shoe box")
        ), onBackClick = {})
    }
}