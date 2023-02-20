package com.joasvpereira.settings.di

import com.joasvpereira.settings.presentation.main.SettingsMainMenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import pt.joasvpereira.core.repository.CurrentSession

val settingsModule = module {
    viewModel {
        SettingsMainMenuViewModel(
            themePreferencesDataSource = get()
        )
    }
}