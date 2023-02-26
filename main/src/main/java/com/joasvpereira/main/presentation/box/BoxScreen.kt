package com.joasvpereira.main.presentation.box

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.joasvpereira.loggger.extentions.logThis
import com.joasvpereira.main.compose.box.BoxContentView
import com.joasvpereira.main.compose.common.container.element.ElementAction
import com.joasvpereira.main.compose.common.popup.CreateItemPopup
import com.joasvpereira.main.compose.common.popup.DeleteItemPopup
import com.joasvpereira.main.domain.data.DivisionElement
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.theme.ThemeOption

@Composable
fun BoxScreen(
    viewModel: BoxScreenViewModel,
    navController: NavController?,
) {
    val theme = viewModel.state.box.parentDivision?.themeOption ?: ThemeOption.THEME_DEFAULT
    DynamicTheme(theme) {
        AnimatedVisibility(visible = viewModel.createOrUpdatePopupState.isVisible) {
            CreateItemPopup(viewModel.createOrUpdatePopupState)
        }

        AnimatedVisibility(visible = viewModel.deleteItemPopupStateHolder.isVisible) {
            DeleteItemPopup(viewModel.deleteItemPopupStateHolder)
        }

        BoxContentView(
            box = DivisionElement.Box(id = viewModel.state.box.id, name = viewModel.state.box.name, description = viewModel.state.box.description),
            itemsList = viewModel.state.list,
            isLoading = viewModel.state.isLoading,
            onBackClick = { navController?.popBackStack() },
            onClick = { item, action ->
                item.logThis(tag = "JVP")
                action.logThis(tag = "JVP")
                when (action) {
                    ElementAction.Delete -> {
                        viewModel.deleteItem(item as DivisionElement.Item)
                    }

                    ElementAction.Edit -> {
                        viewModel.editItem(item as DivisionElement.Item)
                    }

                    ElementAction.Open -> {
                        navController?.navigate("ItemDetailScreen?id=${item.id}")
                    }
                }
            },
            onAddButtonClick = {
                viewModel.createOrUpdatePopupState.apply {
                    isOnEditMode = false
                    name = ""
                    description = ""
                    isVisible = true
                }
            },
        )
    }
}
