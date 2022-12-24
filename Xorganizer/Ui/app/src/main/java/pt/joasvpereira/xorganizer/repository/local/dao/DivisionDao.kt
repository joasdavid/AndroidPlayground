package pt.joasvpereira.xorganizer.repository.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pt.joasvpereira.xorganizer.repository.local.entities.Division
import pt.joasvpereira.xorganizer.repository.local.entities.Division.Companion.TABLE_NAME
import pt.joasvpereira.xorganizer.repository.local.entities.Division.Companion.ID
import pt.joasvpereira.xorganizer.repository.local.entities.Division.Companion.SESSION_ID

@Dao
interface  DivisionDao {
    @Query("SELECT * FROM $TABLE_NAME WHERE $SESSION_ID = :fromSessionId")
    fun getAll(fromSessionId: Int): List<Division>

    @Query("SELECT * FROM $TABLE_NAME WHERE $ID = :id")
    fun getDivision(id: Int): Division

    @Insert
    fun insertDivision(division: Division)
}