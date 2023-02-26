package com.joasvpereira.main.presentation.details

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.joasvpereira.main.compose.details.ItemDetailView
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.theme.ThemeOption

@Composable
fun ItemDetailScreen(
    viewModel: ItemDetailScreenViewModel,
    navController: NavController?,
) {
    viewModel.notFoundPopupState.onButtonPositiveClick = {
        navController?.popBackStack()
    }

    DynamicTheme(option = viewModel.state.itemDetail.parentDivision.themeOption ?: ThemeOption.THEME_DEFAULT) {
        ItemDetailView(
            details = viewModel.state.itemDetail,
            onBackClick = { navController?.popBackStack() },
            isLoading = viewModel.state.isLoading,
            createPopupState = viewModel.updateDetailsState,
            deleteItemPopupState = viewModel.deleteItemState,
            notFoundState = viewModel.notFoundPopupState,
        )
    }
}
