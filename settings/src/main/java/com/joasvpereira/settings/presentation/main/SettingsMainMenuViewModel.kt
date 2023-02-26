package com.joasvpereira.settings.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joasvpereira.sessioncore.domail.usecases.ISessionUseCase
import com.joasvpereira.sessioncore.domail.usecases.SessionIdParam
import com.joasvpereira.sessioncore.repository.SessionPreferencesDataSource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import pt.joasvpereira.core.domain.data.SessionItem
import pt.joasvpereira.core.repository.CurrentSession
import pt.joasvpereira.core.settings.domain.data.ThemePreference
import pt.joasvpereira.core.settings.repository.ThemePreferencesDataSource

class SettingsMainMenuViewModel(
    private val themePreferencesDataSource: ThemePreferencesDataSource,
    private val sessionUseCase: ISessionUseCase,
    private val sessionPreferencesDataSource: SessionPreferencesDataSource,
) : ViewModel() {
    private var _state by mutableStateOf(SettingsMainMenuScreenState.empty())
    val state: SettingsMainMenuScreenState
        get() = _state

    init {
        viewModelScope.launch {
            sessionPreferencesDataSource.getDefaultSessionId().collectLatest {
                _state = state.copy(isKeepSession = (it == CurrentSession.sessionId))
            }
        }
    }

    init {
        viewModelScope.launch {
            val currentSessionId = CurrentSession.sessionIdFlow.first()
            currentSessionId?.run {
                sessionUseCase.execute(SessionIdParam(this)).collect {
                    _state = state.copy(
                        sessionItem = SessionItem(
                            id = it.id,
                            name = it.name,
                            image = it.image,
                        ),
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
                    themeModeSelectedOption = it.mode,
                )
            }
        }
    }

    fun toggleKeepSession(isOn: Boolean) {
        val id = if (state.isKeepSession) -1 else CurrentSession.sessionId ?: -1
        viewModelScope.launch {
            sessionPreferencesDataSource.updatePreference(id)
        }
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
