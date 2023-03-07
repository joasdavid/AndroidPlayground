package com.joasvpereira.sessioncore.domail.usecases

import com.joasvpereira.loggger.extentions.logThis
import com.joasvpereira.sessioncore.repository.SessionPreferencesDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.EmptyParams
import pt.joasvpereira.core.repository.CurrentSession

interface ILoadSessionUseCase : BaseUseCaseSync<EmptyParams, Boolean>

class LoadSessionUseCase(
    private val sessionsUseCase: ISessionsUseCase,
    private val sessionPreferencesDataSource: SessionPreferencesDataSource,
) : ILoadSessionUseCase {

    override suspend fun execute(params: EmptyParams): Boolean = withContext(Dispatchers.IO) {
        val defaultID = sessionPreferencesDataSource.getDefaultSessionId().first()
        val sessions = sessionsUseCase.execute(params).first()
        if (defaultID > 0) {
            sessions.first { it.id == defaultID }.let {
                CurrentSession.sessionIdFlow.value = it.id
                return@withContext true
            }
        }

        "Load session found ${sessions.size} sessions".logThis(tag = "JVP")
        if (sessions.size == 1) {
            CurrentSession.sessionIdFlow.value = sessions.first().logThis(tag = "JVP") {
                "load session = $sessions"
            }.id
            return@withContext true
        }

        return@withContext true
    }
}
