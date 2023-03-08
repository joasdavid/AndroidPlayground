package pt.joasvpereira.core.settings.di

import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pt.joasvpereira.core.repository.local.Db
import pt.joasvpereira.core.settings.repository.ThemePreferencesDataSource
import pt.joasvpereira.core.settings.repository.local.LocalThemePreferences

val coreDependencies = module {
    single<ThemePreferencesDataSource> { LocalThemePreferences(androidContext()) }

    single {
        Room.databaseBuilder(
            androidApplication(),
            Db::class.java,
            "database-name",
        ).addCallback(object : RoomDatabase.Callback() {}).build()
    }
}
