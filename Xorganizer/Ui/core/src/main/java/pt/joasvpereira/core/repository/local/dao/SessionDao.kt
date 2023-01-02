package pt.joasvpereira.core.repository.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import pt.joasvpereira.core.repository.local.entities.Session
import pt.joasvpereira.core.repository.local.entities.Session.Companion.ID_SESSION
import pt.joasvpereira.core.repository.local.entities.Session.Companion.TABLE_NAME

@Dao
interface  SessionDao {
    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): List<Session>

    @Query("SELECT * FROM $TABLE_NAME WHERE $ID_SESSION = :id")
    fun getSession(id: Int): Session?

    @Insert(onConflict = IGNORE)
    fun insertSession(session: Session)

    @Delete
    fun deleteSession(session: Session)
}