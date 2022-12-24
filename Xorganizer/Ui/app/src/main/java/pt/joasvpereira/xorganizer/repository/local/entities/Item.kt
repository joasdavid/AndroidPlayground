package pt.joasvpereira.xorganizer.repository.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import pt.joasvpereira.xorganizer.repository.local.entities.Item.Companion.PARENT_BOX_ID
import pt.joasvpereira.xorganizer.repository.local.entities.Item.Companion.PARENT_DIVISION_ID
import pt.joasvpereira.xorganizer.repository.local.entities.Item.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = Box::class,
            parentColumns = arrayOf(Box.ID),
            childColumns = arrayOf(PARENT_BOX_ID),
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Division::class,
            parentColumns = arrayOf(Division.ID),
            childColumns = arrayOf(PARENT_DIVISION_ID),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Item(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    var id: Int? = null,
    @ColumnInfo(name = NAME)
    val name: String,
    @ColumnInfo(name = DESCRIPTION)
    val description: String,
    @ColumnInfo(name = PARENT_DIVISION_ID)
    val parentDivisionId: Int,
    @ColumnInfo(name = PARENT_BOX_ID)
    val parentBoxId: Int?,
) {

    companion object {
        const val TABLE_NAME = "item"
        const val ID: String = "id"
        const val NAME: String = "name"
        const val DESCRIPTION: String = "description"
        const val PARENT_DIVISION_ID: String = "parent_division_id"
        const val PARENT_BOX_ID: String = "parent_box_id"
    }
}