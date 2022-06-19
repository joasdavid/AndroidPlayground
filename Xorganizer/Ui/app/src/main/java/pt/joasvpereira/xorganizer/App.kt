package pt.joasvpereira.xorganizer

import android.app.Application
import com.google.android.material.color.DynamicColors
import org.koin.core.context.startKoin
import pt.joasvpereira.xorganizer.di.repository
import pt.joasvpereira.xorganizer.di.usecases
import pt.joasvpereira.xorganizer.di.viewModelModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        startKoin {
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