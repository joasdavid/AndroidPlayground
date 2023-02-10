package com.joasvpereira.main.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.repository.local.dao.BoxDao
import pt.joasvpereira.core.repository.local.dao.DivisionDao
import pt.joasvpereira.core.repository.local.dao.ItemDao
import pt.joasvpereira.core.repository.local.dao.SessionDao
import pt.joasvpereira.core.repository.local.entities.Box
import pt.joasvpereira.core.repository.local.entities.Division
import pt.joasvpereira.core.repository.local.entities.Item

interface ItemDataSource {
    suspend fun getDivisionItems(divisionId: Int): Flow<List<Item>>
    suspend fun getBoxItems(boxId: Int): Flow<List<Item>>
    suspend fun getItem(id : Int): Item?
    suspend fun createNewItem(item: Item)
    suspend fun updateItem(item: Item)
    suspend fun deleteItem(id: Int)
}

class LocalItemDataSource(private val itemDao: ItemDao) : ItemDataSource {

    override suspend fun getDivisionItems(divisionId: Int): Flow<List<Item>> = withContext(Dispatchers.IO) {
        itemDao.getAllFromDivision(divisionId)
    }

    override suspend fun getBoxItems(boxId: Int): Flow<List<Item>> = withContext(Dispatchers.IO) {
        itemDao.getAllFromBox(boxId)
    }

    override suspend fun getItem(id: Int): Item? = withContext(Dispatchers.IO){
        kotlin.runCatching { itemDao.getItem(id) }.getOrNull()
    }

    override suspend fun createNewItem(item: Item) = withContext(Dispatchers.IO){
        itemDao.insertItem(item)
    }

    override suspend fun updateItem(item: Item) = withContext(Dispatchers.IO){
        itemDao.updateItem(item)
    }

    override suspend fun deleteItem(id: Int) = withContext(Dispatchers.IO){
        itemDao.deleteItem(itemDao.getItem(id))
    }
}