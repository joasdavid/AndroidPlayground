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
    private val sessionPreferencesDataSource: SessionPreferencesDataSource
) : ILoadSessionUseCase {

    override suspend fun execute(params: EmptyParams): Boolean = withContext(Dispatchers.IO) {
        val defaultID = sessionPreferencesDataSource.getDefaultSessionId().first()
        sessionsUseCase.execute(params).run {

            if (defaultID > 0) {
                first { it.id == defaultID }.let {
                    CurrentSession.sessionFlow.value = it
                    return@withContext true
                }
            }

            "Load session found $size sessions".logThis(tag = "JVP")
            if (size == 1) {
                CurrentSession.sessionFlow.value = this.first().logThis(tag = "JVP") {
                    "load session = $it"
                }
                return@withContext true
            }

            return@withContext false
        }
    }
}