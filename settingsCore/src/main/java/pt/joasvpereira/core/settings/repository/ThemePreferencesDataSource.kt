package pt.joasvpereira.core.settings.repository

import kotlinx.coroutines.flow.Flow
import pt.joasvpereira.core.settings.domain.data.ThemePreference

interface ThemePreferencesDataSource {
    suspend fun updatePreference(pref: ThemePreference)
    suspend fun updateMaterialYou(enabled: Boolean)
    suspend fun updateThemeMode(mode: ThemePreference.ThemeMode)
    fun getUserFromPreferencesStore(): Flow<ThemePreference>
}
