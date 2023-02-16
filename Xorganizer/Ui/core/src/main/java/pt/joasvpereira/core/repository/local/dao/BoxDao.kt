package pt.joasvpereira.core.repository.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pt.joasvpereira.core.repository.local.entities.Box
import pt.joasvpereira.core.repository.local.entities.Box.Companion.ID
import pt.joasvpereira.core.repository.local.entities.Box.Companion.PARENT_DIVISION_ID
import pt.joasvpereira.core.repository.local.entities.Box.Companion.TABLE_NAME
import pt.joasvpereira.core.repository.local.entities.BoxCountAndParentId

@Dao
interface  BoxDao {
    @Query("SELECT * FROM $TABLE_NAME WHERE $PARENT_DIVISION_ID = :fromDivision")
    fun getAll(fromDivision: Int): Flow<List<Box>>

    @Query("SELECT * FROM $TABLE_NAME WHERE $ID = :id")
    fun getBox(id: Int): Flow<Box?>

    @Insert
    fun insertBox(box: Box)

    @Update(onConflict = REPLACE)
    fun updateBox(box: Box)

    @Delete
    fun deleteBox(box: Box)

    @Query("SELECT COUNT($PARENT_DIVISION_ID) AS ${BoxCountAndParentId.COUNT}, $PARENT_DIVISION_ID FROM $TABLE_NAME GROUP BY $PARENT_DIVISION_ID")
    fun getBoxCount(): Flow<List<BoxCountAndParentId>>
}