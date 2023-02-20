package com.joasvpereira.settings.presentation.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.joasvpereira.settings.compose.main.SettingsMainView

@Composable
fun SettingsMainMenuScreen(
    viewModel: SettingsMainMenuViewModel,
    navController: NavController? = null,
    onEditProfile: () -> Unit = {},
    onLogout: () -> Unit
) {

    SettingsMainView(
        onBackClick = { navController?.popBackStack() },
        currentSessionItem = viewModel.state.sessionItem,
        isKeepSession = viewModel.state.isKeepSession,
        onKeepSessionChange = { viewModel.toggleKeepSession(it) },
        onEditProfile = onEditProfile,
        isMaterialYouEnabled = viewModel.state.isMaterialYouEnabled,
        onMaterialYouSwitchChange = { viewModel.toggleMaterialYou(it) },
        themeModeSelectedOption = viewModel.state.themeModeSelectedOption,
        onThemeModeChange = { viewModel.updateThemeMode(it) },
        onLogout = onLogout
    )
}