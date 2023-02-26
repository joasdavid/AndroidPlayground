package pt.joasvpereira.core.repository

import kotlinx.coroutines.flow.MutableStateFlow

object CurrentSession {
    val sessionIdFlow: MutableStateFlow<Int?> = MutableStateFlow(null)
    val sessionId: Int?
        get() = sessionIdFlow.value
}
