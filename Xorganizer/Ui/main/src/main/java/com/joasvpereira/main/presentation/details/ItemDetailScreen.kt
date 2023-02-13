package com.joasvpereira.main.presentation.details

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.joasvpereira.main.compose.details.ItemDetailView
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.ThemeOption

@Composable
fun ItemDetailScreen(
    viewModel: ItemDetailScreenViewModel,
    navController: NavController?
) {
    DynamicTheme(option = viewModel.state.itemDetail.parentDivision.themeOption ?: ThemeOption.THEME_DEFAULT) {
        ItemDetailView(
            details = viewModel.state.itemDetail,
            onBackClick = { navController?.popBackStack() },
            isLoading = viewModel.state.isLoading,
            createPopupState = viewModel.updateDetailsState
        )
    }
}