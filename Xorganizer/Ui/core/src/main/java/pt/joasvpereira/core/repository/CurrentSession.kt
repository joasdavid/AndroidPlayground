package pt.joasvpereira.core.repository

import pt.joasvpereira.core.repository.local.entities.Session

internal object CurrentSession {
    var session : Session? = null
}