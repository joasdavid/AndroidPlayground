package com.joasvpereira.main.repository

import com.joasvpereira.loggger.extentions.logThis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.repository.local.dao.DivisionDao
import pt.joasvpereira.core.repository.local.entities.Division

interface DivisionDataSource {
    suspend fun getDivisions(): Flow<List<Division>>
    suspend fun getDivision(id: Int): Division?
    suspend fun createNewDivision(division: Division)
    suspend fun updateDivision(division: Division)
    suspend fun deleteDivision(id: Int)
}

class LocalDivisionDataSource(private val targetSessionId: Int, private val divisionDao: DivisionDao) : DivisionDataSource {
    override suspend fun getDivisions(): Flow<List<Division>> = withContext(Dispatchers.IO) {
        "Fetch divisions for session id = $targetSessionId".logThis()
        divisionDao.getAll(targetSessionId)
    }

    override suspend fun getDivision(id: Int): Division? = withContext(Dispatchers.IO) {
        kotlin.runCatching { divisionDao.getDivision(id) }.getOrNull()
    }

    override suspend fun createNewDivision(division: Division) = withContext(Dispatchers.IO) {
        divisionDao.insertDivision(division)
    }

    override suspend fun updateDivision(division: Division) {
        divisionDao.updateDivision(division)
    }

    override suspend fun deleteDivision(id: Int) = withContext(Dispatchers.IO) {
        divisionDao.deleteDivision(id)
    }
}
