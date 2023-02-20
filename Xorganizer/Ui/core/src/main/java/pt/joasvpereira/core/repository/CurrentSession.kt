package pt.joasvpereira.core.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import pt.joasvpereira.core.domain.data.SessionItem
import pt.joasvpereira.core.repository.local.entities.Session

object CurrentSession {
    val sessionFlow : MutableStateFlow<SessionItem?> = MutableStateFlow(null)
    val session: SessionItem?
        get() = sessionFlow.value
}