package pt.joasvpereira.core.repository.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pt.joasvpereira.core.repository.local.entities.Division
import pt.joasvpereira.core.repository.local.entities.Division.Companion.ID
import pt.joasvpereira.core.repository.local.entities.Division.Companion.SESSION_ID
import pt.joasvpereira.core.repository.local.entities.Division.Companion.TABLE_NAME

@Dao
interface  DivisionDao {
    @Query("SELECT * FROM $TABLE_NAME WHERE $SESSION_ID = :fromSessionId")
    fun getAll(fromSessionId: Int): Flow<List<Division>>

    @Query("SELECT * FROM $TABLE_NAME WHERE $ID = :id")
    fun getDivision(id: Int): Division

    @Insert
    fun insertDivision(division: Division)

    @Update(onConflict =  OnConflictStrategy.REPLACE)
    fun updateDivision(division: Division)

    @Query("DELETE FROM $TABLE_NAME WHERE $ID = :id")
    fun deleteDivision(id: Int)
}