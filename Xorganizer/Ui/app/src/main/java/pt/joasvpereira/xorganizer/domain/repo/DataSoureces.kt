package pt.joasvpereira.xorganizer.domain.repo

import kotlinx.coroutines.flow.Flow
import pt.joasvpereira.xorganizer.domain.model.Box
import pt.joasvpereira.xorganizer.domain.model.Division
import pt.joasvpereira.xorganizer.domain.model.StoredItem

interface DivisionDataSource {
    suspend fun getDivisions(): Flow<List<Division>>
    suspend fun singleDivisions(id : Int): Division
    suspend fun addDivision(division: Division)
}

interface BoxDataSource {
    suspend fun getBoxes(from: FromDivision): Flow<List<Box>>
    suspend fun singleBox(id : Int): Box
}

interface StoredItemDataSource {
    suspend fun getItems(from: FromDivision): Flow<List<StoredItem>>
    suspend fun getItems(from: FromBox): Flow<List<StoredItem>>
    suspend fun singleItem(id : Int): StoredItem
}

data class FromDivision(val id: Int)
data class FromBox(val id: Int)