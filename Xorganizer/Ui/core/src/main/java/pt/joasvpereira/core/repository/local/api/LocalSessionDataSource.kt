package pt.joasvpereira.core.repository.local.api

import pt.joasvpereira.core.repository.local.dao.SessionDao

class LocalSessionDataSource(private val sessionDao: SessionDao) /*: SessionDataSource {
    override suspend fun geSession(id: Int): Session? = sessionDao.getSession(id)?.also {
        CurrentSession.session = it
    }

    override suspend fun createNewSession(session: Session) {
        sessionDao.insertSession(session)
    }
}*/