package pt.joasvpereira.xorganizer

import android.app.Application
import com.google.android.material.color.DynamicColors
import com.joasvpereira.loggger.Logger
import com.joasvpereira.main.di.MainFeatureModule
import com.joasvpereira.sessioncore.di.sessionCoreModule
import com.joasvpereira.settings.di.settingsModule
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pt.joasvpereira.core.settings.di.coreDependencies
import pt.joasvpereira.xorganizer.di.repository
import pt.joasvpereira.xorganizer.di.sessionFeatureModule
import pt.joasvpereira.xorganizer.di.usecases
import pt.joasvpereira.xorganizer.di.viewModelModule

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
                    settingsModule,
                    sessionCoreModule,
                )
            )
        }

        Logger.apply {
            defaultTag = "JVP"
            defaultLevel = Logger.Level.DEBUG
        }
    }
}