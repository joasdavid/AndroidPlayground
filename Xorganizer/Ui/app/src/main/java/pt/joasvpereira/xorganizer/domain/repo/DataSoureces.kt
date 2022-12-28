package pt.joasvpereira.xorganizer.domain.repo

import pt.joasvpereira.xorganizer.domain.model.Box
import pt.joasvpereira.xorganizer.domain.model.Division
import pt.joasvpereira.xorganizer.domain.model.StoredItem
import pt.joasvpereira.core.repository.local.entities.Session

interface SessionDataSource {
    suspend fun geSession(id : Int): Session?
    suspend fun createNewSession(session: Session)
}

interface DivisionDataSource {
    suspend fun getDivisions(): List<Division>
    suspend fun singleDivisions(id : Int): Division
    suspend fun addDivision(division: Division)
}

interface BoxDataSource {
    suspend fun getBoxes(from: FromDivision): List<Box>
    suspend fun singleBox(id : Int): Box
}

interface StoredItemDataSource {
    suspend fun getItems(from: FromDivision): List<StoredItem>
    suspend fun getItems(from: FromBox): List<StoredItem>
    suspend fun singleItem(id : Int): StoredItem
}

data class FromSession(val id: Int)
data class FromDivision(val id: Int)
data class FromBox(val id: Int)