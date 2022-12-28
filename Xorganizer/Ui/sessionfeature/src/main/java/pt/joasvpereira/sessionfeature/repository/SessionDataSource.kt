package pt.joasvpereira.sessionfeature.repository

import pt.joasvpereira.core.repository.local.entities.Session

interface SessionDataSource {
    suspend fun geSessions(): List<Session>
    //suspend fun geSession(id : Int): Session?
    //suspend fun createNewSession(session: Session)
}