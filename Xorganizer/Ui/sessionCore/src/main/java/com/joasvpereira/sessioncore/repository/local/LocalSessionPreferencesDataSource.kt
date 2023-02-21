package com.joasvpereira.sessioncore.repository.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.joasvpereira.sessioncore.repository.SessionPreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.sessionPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "sessionPref"
)

private val DEFAULT_SESSION_ID = intPreferencesKey("default_session_id")

class LocalSessionPreferencesDataSource(private val context: Context) : SessionPreferencesDataSource {
    override suspend fun updatePreference(id: Int) {
        context.sessionPreferencesDataStore.edit { preferences ->
            preferences[DEFAULT_SESSION_ID] = id
        }
    }

    override fun getDefaultSessionId(): Flow<Int> = context
        .sessionPreferencesDataStore
        .data
        .map { preferences ->
            preferences[DEFAULT_SESSION_ID] ?: -1
        }
}