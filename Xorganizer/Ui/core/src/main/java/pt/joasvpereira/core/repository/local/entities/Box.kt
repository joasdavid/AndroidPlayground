package pt.joasvpereira.core.repository.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import pt.joasvpereira.core.repository.local.entities.Box.Companion.PARENT_DIVISION_ID
import pt.joasvpereira.core.repository.local.entities.Box.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = Division::class,
            parentColumns = arrayOf(Division.ID),
            childColumns = arrayOf(PARENT_DIVISION_ID),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Box(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    var id: Int? = null,
    @ColumnInfo(name = NAME)
    val name: String,
    @ColumnInfo(name = DESCRIPTION)
    val description: String = "",
    @ColumnInfo(name = PARENT_DIVISION_ID)
    val parentDivisionId: Int,
) {

    companion object {
        const val TABLE_NAME = "box"
        const val ID: String = "id"
        const val NAME: String = "name"
        const val DESCRIPTION: String = "description"
        const val PARENT_DIVISION_ID: String = "parent_division_id"
    }
}