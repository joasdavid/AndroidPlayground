package com.joasvpereira.main.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.repository.local.dao.DivisionDao
import pt.joasvpereira.core.repository.local.dao.SessionDao
import pt.joasvpereira.core.repository.local.entities.Division

interface DivisionDataSource {
    suspend fun getDivisions(): List<Division>
    suspend fun getDivision(id : Int): Division?
    suspend fun createNewDivision(division: Division)
    suspend fun deleteDivision(id: Int)
}

class LocalDivisionDataSource(private val targetSessionId: Int, private val divisionDao: DivisionDao) : DivisionDataSource {
    override suspend fun getDivisions(): List<Division> = withContext(Dispatchers.IO) {
        divisionDao.getAll(targetSessionId)
    }

    override suspend fun getDivision(id: Int): Division? = withContext(Dispatchers.IO) {
        kotlin.runCatching { divisionDao.getDivision(id) }.getOrNull()
    }

    override suspend fun createNewDivision(division: Division) = withContext(Dispatchers.IO) {
        divisionDao.insertDivision(division)
    }

    override suspend fun deleteDivision(id: Int) = withContext(Dispatchers.IO) {
        divisionDao.deleteDivision(id)
    }
}