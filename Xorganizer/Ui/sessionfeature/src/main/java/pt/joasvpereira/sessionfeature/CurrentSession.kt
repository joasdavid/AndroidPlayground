package pt.joasvpereira.sessionfeature

import pt.joasvpereira.sessionfeature.domain.data.SessionItem

object CurrentSession {
    internal var _session : SessionItem? = null

    val session: SessionItem?
    get() = _session
}