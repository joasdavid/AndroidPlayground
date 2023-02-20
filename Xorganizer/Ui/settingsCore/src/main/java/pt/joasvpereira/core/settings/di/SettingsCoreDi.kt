package pt.joasvpereira.core.settings.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pt.joasvpereira.core.settings.repository.ThemePreferencesDataSource
import pt.joasvpereira.core.settings.repository.local.LocalThemePreferences

val coreDependencies = module {
    single<ThemePreferencesDataSource> { LocalThemePreferences(androidContext()) }
}