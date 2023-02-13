package pt.joasvpereira.core.repository.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pt.joasvpereira.core.repository.local.entities.Item
import pt.joasvpereira.core.repository.local.entities.Item.Companion.ID
import pt.joasvpereira.core.repository.local.entities.Item.Companion.PARENT_BOX_ID
import pt.joasvpereira.core.repository.local.entities.Item.Companion.PARENT_DIVISION_ID
import pt.joasvpereira.core.repository.local.entities.Item.Companion.TABLE_NAME
import pt.joasvpereira.core.repository.local.entities.ItemCountAndParentId

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
    @Update(onConflict = REPLACE)
    fun updateItem(item: Item)

    @Delete
    fun deleteItem(item: Item)

    @Query("SELECT COUNT($PARENT_DIVISION_ID) AS ${ItemCountAndParentId.COUNT}, $PARENT_DIVISION_ID FROM $TABLE_NAME GROUP BY $PARENT_DIVISION_ID")
    fun getItemCount(): Flow<List<ItemCountAndParentId>>
}