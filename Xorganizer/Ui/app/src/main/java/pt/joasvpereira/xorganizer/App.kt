package pt.joasvpereira.xorganizer

import android.app.Application
import com.google.android.material.color.DynamicColors
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
                    repository
                )
            )
        }
    }
}