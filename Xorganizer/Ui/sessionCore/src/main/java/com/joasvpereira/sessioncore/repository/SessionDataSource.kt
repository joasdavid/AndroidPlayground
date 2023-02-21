package pt.joasvpereira.sessionfeature.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.repository.local.dao.SessionDao
import pt.joasvpereira.core.repository.local.entities.Session

interface SessionDataSource {
    suspend fun geSessions(): Flow<List<Session>>
    suspend fun geSession(id: Int): Flow<Session>
    suspend fun createNewSession(session: Session)
    suspend fun updateNewSession(session: Session)
    suspend fun deleteSession(id: Int)
}

class LocalSessionDataSource(private val sessionDao: SessionDao) : SessionDataSource {
    override suspend fun geSessions(): Flow<List<Session>> = withContext(Dispatchers.IO) {
        sessionDao.getAll()
    }

    override suspend fun geSession(id: Int) = withContext(Dispatchers.IO) {
        sessionDao.getSession(id)
    }

    override suspend fun createNewSession(session: Session) = withContext(Dispatchers.IO) {
        sessionDao.insertSession(session)
    }

    override suspend fun updateNewSession(session: Session) = withContext(Dispatchers.IO) {
        sessionDao.updateSession(session)
    }

    override suspend fun deleteSession(id: Int) = withContext(Dispatchers.IO) {
        sessionDao.deleteSession(id)
    }

}