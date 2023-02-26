package pt.joasvpereira.core.repository.local.entities

import androidx.room.ColumnInfo
import pt.joasvpereira.core.repository.local.entities.Item.Companion.PARENT_DIVISION_ID

data class ItemCountAndParentId(
    @ColumnInfo(name = COUNT)
    var count: Int,
    @ColumnInfo(name = PARENT_DIVISION_ID)
    val parentDivisionId: Int,
) {

    companion object {
        const val COUNT: String = "count"
    }
}
