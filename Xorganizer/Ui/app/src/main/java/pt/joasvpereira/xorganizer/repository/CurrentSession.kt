package pt.joasvpereira.xorganizer.repository

import pt.joasvpereira.xorganizer.repository.local.entities.Session

internal object CurrentSession {
    var session : Session? = null
}