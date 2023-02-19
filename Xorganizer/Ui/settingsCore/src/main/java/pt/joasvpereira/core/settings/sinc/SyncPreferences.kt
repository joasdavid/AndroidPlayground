package pt.joasvpereira.core.settings.sinc

import com.joasvpereira.loggger.extentions.logThis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.get
import pt.joasvpereira.core.settings.InternalThemePref
import pt.joasvpereira.core.settings.repository.ThemePreferencesDataSource

class SyncPreferences(private val dataSource: ThemePreferencesDataSource) {
    suspend fun startSyncPreferences() {
        withContext(Dispatchers.Main) {
            dataSource.getUserFromPreferencesStore().collect {
                "Updating InternalThemPref with $it".logThis()
                InternalThemePref.apply {
                    mode = it.mode
                    isMaterialYou = it.isMaterialYouEnabled
                }
            }
        }
    }
}