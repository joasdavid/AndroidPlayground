package com.joasvpereira.sessioncore.repository

import kotlinx.coroutines.flow.Flow

interface SessionPreferencesDataSource {
    suspend fun updatePreference(id: Int)
    fun getDefaultSessionId(): Flow<Int>
}
