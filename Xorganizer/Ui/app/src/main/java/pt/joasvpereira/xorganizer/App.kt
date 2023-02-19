package pt.joasvpereira.xorganizer

import android.app.Application
import com.google.android.material.color.DynamicColors
import com.joasvpereira.loggger.Logger
import com.joasvpereira.main.di.MainFeatureModule
import com.joasvpereira.settings.di.settingsModule
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pt.joasvpereira.xorganizer.di.repository
import pt.joasvpereira.xorganizer.di.usecases
import pt.joasvpereira.xorganizer.di.viewModelModule
import pt.joasvpereira.core.repository.local.Db
import pt.joasvpereira.core.repository.local.entities.Session
import pt.joasvpereira.core.settings.di.coreDependencies
import pt.joasvpereira.core.settings.domain.data.ThemePreference
import pt.joasvpereira.core.settings.repository.ThemePreferencesDataSource
import pt.joasvpereira.core.settings.sinc.SyncPreferences
import pt.joasvpereira.xorganizer.di.sessionFeatureModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        startKoin {
            // declare used Android context
            androidContext(this@App)
            modules(
                listOf(
                    viewModelModule,
                    usecases,
                    repository,
                    sessionFeatureModule,
                    MainFeatureModule,
                    coreDependencies,
                    settingsModule
                )
            )
        }

        Logger.apply {
            defaultTag = "JVP"
            defaultLevel = Logger.Level.DEBUG
        }

        GlobalScope.launch {
            get<SyncPreferences>().startSyncPreferences()
        }
    }
}