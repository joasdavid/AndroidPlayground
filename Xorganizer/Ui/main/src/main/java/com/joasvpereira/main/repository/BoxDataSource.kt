package com.joasvpereira.main.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.repository.local.dao.BoxDao
import pt.joasvpereira.core.repository.local.entities.Box
import pt.joasvpereira.core.repository.local.entities.BoxCountAndParentId

interface BoxDataSource {
    suspend fun getBoxes(divisionId: Int): Flow<List<Box>>
    suspend fun getBox(id : Int): Flow<Box?>
    suspend fun createNewBox(box: Box)
    suspend fun updateBox(box: Box)
    suspend fun deleteBox(id: Int)
    suspend fun countBoxFrom(): Flow<List<BoxCountAndParentId>>
}

class LocalBoxDataSource(private val boxDao: BoxDao) : BoxDataSource {

    override suspend fun getBoxes(divisionId: Int) = withContext(Dispatchers.IO) {
        boxDao.getAll(divisionId)
    }

    override suspend fun getBox(id: Int): Flow<Box?> = withContext(Dispatchers.IO){
        boxDao.getBox(id)
    }

    override suspend fun createNewBox(box: Box) = withContext(Dispatchers.IO){
        boxDao.insertBox(box)
    }

    override suspend fun updateBox(box: Box) = withContext(Dispatchers.IO){
        boxDao.updateBox(box)
    }

    override suspend fun deleteBox(id: Int) : Unit = withContext(Dispatchers.IO){
        boxDao.getBox(id).first()?.run {
            boxDao.deleteBox(this)
        }
    }

    override suspend fun countBoxFrom(): Flow<List<BoxCountAndParentId>> = withContext(Dispatchers.IO) {
        boxDao.getBoxCount()
    }
}