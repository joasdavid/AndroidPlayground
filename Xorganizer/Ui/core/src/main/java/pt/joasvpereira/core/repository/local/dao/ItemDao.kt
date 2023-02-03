package pt.joasvpereira.core.repository.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pt.joasvpereira.core.repository.local.entities.Item
import pt.joasvpereira.core.repository.local.entities.Item.Companion.ID
import pt.joasvpereira.core.repository.local.entities.Item.Companion.PARENT_BOX_ID
import pt.joasvpereira.core.repository.local.entities.Item.Companion.PARENT_DIVISION_ID
import pt.joasvpereira.core.repository.local.entities.Item.Companion.TABLE_NAME

@Dao
interface  ItemDao {
    @Query("SELECT * FROM $TABLE_NAME WHERE $PARENT_DIVISION_ID = :fromDivision and $PARENT_BOX_ID is null")
    fun getAllFromDivision(fromDivision: Int): Flow<List<Item>>

    @Query("SELECT * FROM $TABLE_NAME WHERE $PARENT_BOX_ID = :fromBox")
    fun getAllFromBox(fromBox: Int): Flow<List<Item>>

    @Query("SELECT * FROM $TABLE_NAME WHERE $ID = :id")
    fun getItem(id: Int): Item

    @Insert
    fun insertItem(item: Item)

    @Delete
    fun deleteItem(item: Item)
}