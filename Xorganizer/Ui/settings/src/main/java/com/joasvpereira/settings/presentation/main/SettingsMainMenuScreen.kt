package com.joasvpereira.settings.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.joasvpereira.settings.compose.main.SettingsMainView
import pt.joasvpereira.core.settings.domain.data.ThemePreference
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.shouldDisplayDarkTheme

@Composable
fun SettingsMainMenuScreen(
    viewModel: SettingsMainMenuViewModel,
    navController: NavController? = null
) {

    DynamicTheme(
        isDynamicColor = viewModel.state.isMaterialYouEnabled,
        isDarkTheme = with(viewModel.state.themeModeSelectedOption) { shouldDisplayDarkTheme() }
    ) {
        SettingsMainView(
            onBackClick = { navController?.popBackStack() },
            currentSessionItem = viewModel.state.sessionItem,
            isKeepSession = viewModel.state.isKeepSession,
            onKeepSessionChange = { viewModel.toggleKeepSession(it) },
            onEditProfile = { },
            isMaterialYouEnabled = viewModel.state.isMaterialYouEnabled,
            onMaterialYouSwitchChange = { viewModel.toggleMaterialYou(it) },
            themeModeSelectedOption = viewModel.state.themeModeSelectedOption,
            onThemeModeChange = { viewModel.updateThemeMode(it) }
        )
    }
}