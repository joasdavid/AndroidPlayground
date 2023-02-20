package com.joasvpereira.settings.presentation.main

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import pt.joasvpereira.core.domain.data.SessionItem
import pt.joasvpereira.core.repository.CurrentSession
import pt.joasvpereira.core.settings.domain.data.ThemePreference
import pt.joasvpereira.core.settings.repository.ThemePreferencesDataSource
import pt.joasvpereira.sessionfeature.repository.SessionDataSource

class SettingsMainMenuViewModel(
    private val themePreferencesDataSource: ThemePreferencesDataSource
) : ViewModel() {
    private var _state by mutableStateOf(SettingsMainMenuScreenState.empty())
    val state: SettingsMainMenuScreenState
        get() = _state

    init {
        viewModelScope.launch {
            CurrentSession.sessionFlow.collectLatest {
                it?.run {
                    _state = state.copy(
                        sessionItem = SessionItem(
                            id = id, name = name, image = image
                        )
                    )
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            themePreferencesDataSource.getUserFromPreferencesStore().collectLatest {
                _state = state.copy(
                    isMaterialYouEnabled = it.isMaterialYouEnabled,
                    themeModeSelectedOption = it.mode
                )
            }
        }
    }

    fun toggleKeepSession(isOn: Boolean) {
        _state = _state.copy(isKeepSession = isOn)
    }

    fun toggleMaterialYou(isOn: Boolean) {
        viewModelScope.launch {
            themePreferencesDataSource.updateMaterialYou(isOn)
        }
    }

    fun updateThemeMode(mode: ThemePreference.ThemeMode) {
        viewModelScope.launch {
            themePreferencesDataSource.updateThemeMode(mode)
        }
    }

}