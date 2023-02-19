package pt.joasvpereira.core.settings.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pt.joasvpereira.core.settings.repository.ThemePreferencesDataSource
import pt.joasvpereira.core.settings.repository.local.LocalThemePreferences
import pt.joasvpereira.core.settings.sinc.SyncPreferences

val coreDependencies = module {
    single<ThemePreferencesDataSource> { LocalThemePreferences(androidContext()) }

    single {
        SyncPreferences(get())
    }
}