package pt.joasvpereira.core.repository

import pt.joasvpereira.core.domain.data.SessionItem
import pt.joasvpereira.core.repository.local.entities.Session

object CurrentSession {
    var session: SessionItem? = null
}