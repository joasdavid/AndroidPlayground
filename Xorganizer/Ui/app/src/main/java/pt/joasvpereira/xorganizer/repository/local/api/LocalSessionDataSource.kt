package pt.joasvpereira.xorganizer.repository.local.api

import pt.joasvpereira.xorganizer.domain.repo.SessionDataSource
import pt.joasvpereira.xorganizer.repository.CurrentSession
import pt.joasvpereira.xorganizer.repository.local.dao.SessionDao
import pt.joasvpereira.xorganizer.repository.local.entities.Session

class LocalSessionDataSource(private val sessionDao: SessionDao) : SessionDataSource {
    override suspend fun geSession(id: Int): Session? = sessionDao.getSession(id)?.also {
        CurrentSession.session = it
    }

    override suspend fun createNewSession(session: Session) {
        sessionDao.insertSession(session)
    }
}