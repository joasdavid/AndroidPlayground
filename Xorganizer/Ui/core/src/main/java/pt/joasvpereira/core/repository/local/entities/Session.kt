package pt.joasvpereira.core.repository.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pt.joasvpereira.core.repository.local.entities.Session.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME
)
data class Session(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID_SESSION)
    var id: Int? = null,
    @ColumnInfo(name = DISPLAY_NAME)
    var displayName: String = "Session $id"
) {

    companion object {
        const val TABLE_NAME = "session"
        const val ID_SESSION: String = "idSession"
        const val DISPLAY_NAME: String = "display_name"
    }
}