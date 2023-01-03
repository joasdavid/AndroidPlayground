package pt.joasvpereira.sessionfeature.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.repository.local.dao.SessionDao
import pt.joasvpereira.core.repository.local.entities.Session

interface SessionDataSource {
    suspend fun geSessions(): List<Session>
    //suspend fun geSession(id : Int): Session?
    suspend fun createNewSession(session: Session)
}

class LocalSessionDataSource(private val sessionDao: SessionDao) : SessionDataSource {
    override suspend fun geSessions(): List<Session> = withContext(Dispatchers.IO) {
        sessionDao.getAll()
    }

    override suspend fun createNewSession(session: Session) = withContext(Dispatchers.IO) {
        sessionDao.insertSession(session)
    }

}