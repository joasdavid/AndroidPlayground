package pt.joasvpereira.core.repository

import com.joasvpereira.loggger.Logger
import pt.joasvpereira.core.repository.local.entities.Session

internal object CurrentSession {
    var session : Session? = null
}