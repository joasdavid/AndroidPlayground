package pt.joasvpereira.xorganizer

import android.app.Application
import com.google.android.material.color.DynamicColors
import com.joasvpereira.loggger.Logger
import com.joasvpereira.main.di.MainFeatureModule
import com.joasvpereira.sessioncore.di.sessionCoreModule
import com.joasvpereira.settings.di.settingsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pt.joasvpereira.core.settings.di.coreDependencies
import pt.joasvpereira.sessionfeature.di.sessionFeatureModule
import pt.joasvpereira.xorganizer.di.appModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        startKoin {
            // declare used Android context
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    sessionFeatureModule,
                    MainFeatureModule,
                    coreDependencies,
                    settingsModule,
                    sessionCoreModule,
                ),
            )
        }

        Logger.apply {
            defaultTag = "JVP"
            defaultLevel = Logger.Level.DEBUG
        }
    }
}
