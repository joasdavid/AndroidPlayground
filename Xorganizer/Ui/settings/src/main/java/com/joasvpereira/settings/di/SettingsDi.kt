package com.joasvpereira.settings.di

import com.joasvpereira.settings.presentation.main.SettingsMainMenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    viewModel {
        SettingsMainMenuViewModel(
            themePreferencesDataSource = get(),
            sessionPreferencesDataSource = get(),
        )
    }
}