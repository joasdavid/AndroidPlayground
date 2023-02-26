package com.joasvpereira.settings.presentation.main

import pt.joasvpereira.core.domain.data.SessionItem
import pt.joasvpereira.core.settings.domain.data.ThemePreference

data class SettingsMainMenuScreenState(
    val sessionItem: SessionItem,
    val isKeepSession: Boolean,
    val isMaterialYouEnabled: Boolean,
    val themeModeSelectedOption: ThemePreference.ThemeMode,
) {
    companion object {
        fun empty(): SettingsMainMenuScreenState {
            return SettingsMainMenuScreenState(
                sessionItem = SessionItem(id = 0, name = "", image = null),
                isKeepSession = false,
                isMaterialYouEnabled = false,
                themeModeSelectedOption = ThemePreference.ThemeMode.DEFAULT,
            )
        }
    }
}
