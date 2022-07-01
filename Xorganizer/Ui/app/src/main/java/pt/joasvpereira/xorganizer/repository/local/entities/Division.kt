package pt.joasvpereira.xorganizer.repository.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pt.joasvpereira.xorganizer.repository.local.entities.Division.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
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
) {

    companion object {
        const val TABLE_NAME = "division"
        const val ID: String = "id"
        const val NAME: String = "name"
        const val DESCRIPTION: String = "description"
        const val ICON_ID: String = "icon_id"
        const val THEME_ID: String = "theme_id"
    }
}