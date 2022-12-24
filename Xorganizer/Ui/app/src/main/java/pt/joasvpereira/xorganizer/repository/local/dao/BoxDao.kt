package pt.joasvpereira.xorganizer.repository.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pt.joasvpereira.xorganizer.repository.local.entities.Box
import pt.joasvpereira.xorganizer.repository.local.entities.Box.Companion.ID
import pt.joasvpereira.xorganizer.repository.local.entities.Box.Companion.PARENT_DIVISION_ID
import pt.joasvpereira.xorganizer.repository.local.entities.Box.Companion.TABLE_NAME

@Dao
interface  BoxDao {
    @Query("SELECT * FROM $TABLE_NAME WHERE $PARENT_DIVISION_ID = :fromDivision")
    fun getAll(fromDivision: Int): List<Box>

    @Query("SELECT * FROM $TABLE_NAME WHERE $ID = :id")
    fun getBox(id: Int): Box

    @Insert
    fun insertBox(box: Box)

    @Delete
    fun deleteBox(box: Box)
}