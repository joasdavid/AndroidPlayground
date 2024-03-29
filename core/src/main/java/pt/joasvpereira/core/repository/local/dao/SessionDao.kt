package pt.joasvpereira.core.repository.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pt.joasvpereira.core.repository.local.entities.Session
import pt.joasvpereira.core.repository.local.entities.Session.Companion.ID_SESSION
import pt.joasvpereira.core.repository.local.entities.Session.Companion.TABLE_NAME

@Dao
interface SessionDao {
    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): Flow<List<Session>>

    @Query("SELECT * FROM $TABLE_NAME WHERE $ID_SESSION = :id")
    fun getSession(id: Int): Flow<Session>

    @Insert(onConflict = REPLACE)
    fun insertSession(session: Session)

    @Update(onConflict = REPLACE)
    fun updateSession(session: Session)

    @Query("DELETE FROM $TABLE_NAME WHERE $ID_SESSION = :id")
    fun deleteSession(id: Int)
}
