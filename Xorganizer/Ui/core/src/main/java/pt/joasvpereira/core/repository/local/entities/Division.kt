package pt.joasvpereira.core.repository.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.util.TableInfo.ForeignKey
import pt.joasvpereira.core.repository.local.entities.Division.Companion.SESSION_ID
import pt.joasvpereira.core.repository.local.entities.Division.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = Session::class,
            parentColumns = arrayOf(Session.ID_SESSION),
            childColumns = arrayOf(SESSION_ID),
            onDelete = androidx.room.ForeignKey.CASCADE
        )
    ]
)
data class Division(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    var id: Int? = null,
    @ColumnInfo(name = NAME)
    val name: String,
    @ColumnInfo(name = DESCRIPTION)
    val description: String,
    @ColumnInfo(name = ICON_ID)
    val iconId: Int,
    @ColumnInfo(name = THEME_ID)
    val themeId: Int,
    @ColumnInfo(name = SESSION_ID)
    var sessionId: Int,
) {

    companion object {
        const val TABLE_NAME = "division"
        const val ID: String = "id"
        const val SESSION_ID: String = "session_id"
        const val NAME: String = "name"
        const val DESCRIPTION: String = "description"
        const val ICON_ID: String = "icon_id"
        const val THEME_ID: String = "theme_id"
    }
}