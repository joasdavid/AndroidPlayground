package pt.joasvpereira.core.settings.repository.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pt.joasvpereira.core.settings.domain.data.ThemePreference
import pt.joasvpereira.core.settings.repository.ThemePreferencesDataSource

private val Context.themePreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "themePref",
)

private val SHOULD_USE_MATERIAL_YOU = booleanPreferencesKey("theme_use_material_you")
private val THEME_MODE = stringPreferencesKey("theme_mode")

internal class LocalThemePreferences(private val context: Context) : ThemePreferencesDataSource {

    override suspend fun updatePreference(pref: ThemePreference) {
        context.themePreferencesDataStore.edit { preference ->
            preference[SHOULD_USE_MATERIAL_YOU] = pref.isMaterialYouEnabled
            preference[THEME_MODE] = pref.mode.name
        }
    }

    override suspend fun updateMaterialYou(enabled: Boolean) {
        context.themePreferencesDataStore.edit { preference ->
            preference[SHOULD_USE_MATERIAL_YOU] = enabled
        }
    }

    override suspend fun updateThemeMode(mode: ThemePreference.ThemeMode) {
        context.themePreferencesDataStore.edit { preference ->
            preference[THEME_MODE] = mode.name
        }
    }

    override fun getUserFromPreferencesStore(): Flow<ThemePreference> = context.themePreferencesDataStore.data
        .map { preferences ->
            ThemePreference(
                isMaterialYouEnabled = preferences[SHOULD_USE_MATERIAL_YOU] ?: true,
                mode = preferences[THEME_MODE]?.run { ThemePreference.ThemeMode.valueOf(this) }
                    ?: ThemePreference.ThemeMode.DEFAULT,
            )
        }
}
