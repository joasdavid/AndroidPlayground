package com.joasvpereira.main.presentation.dashboard

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.joasvpereira.main.compose.dashboard.DashboardScreen

@Composable
fun DashboardFeatureScreen(
    viewModel: DashboardFeatureScreenViewModel,
    navController: NavController? = null,
    onSettingsClicked: () -> Unit,
) {
    DashboardScreen(
        isLoading = viewModel.state.isLoading,
        sessionName = viewModel.state.sessionName,
        sessionImage = viewModel.state.sessionImage,
        divisions = viewModel.state.divisions,
        onDivisionClick = { navController?.navigate("DivisionFeatureScreen?id=${it.id}") },
        onAddNewItemClick = { navController?.navigate("CreateDivisionsFeatureScreen") },
        onEditClick = { navController?.navigate("CreateDivisionsFeatureScreen?id=${it.id}") },
        onSettingClicked = onSettingsClicked,
    )
}
