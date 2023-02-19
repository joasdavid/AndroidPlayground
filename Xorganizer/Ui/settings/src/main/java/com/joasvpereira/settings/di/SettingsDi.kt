package com.joasvpereira.settings.di

import com.joasvpereira.settings.presentation.main.SettingsMainMenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val settingsModule = module {
    viewModel {
        SettingsMainMenuViewModel(
            sessionName = get(named("SESSION_NAME")),
            sessionImage = get(named("SESSION_IMAGE")),
            themePreferencesDataSource = get()
        )
    }
}