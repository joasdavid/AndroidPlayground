package pt.joasvpereira.xorganizer.presentation.mapper

import pt.joasvpereira.xorganizer.domain.model.StoredItem
import pt.joasvpereira.xorganizer.presentation.compose.division.SingleItem

class ItemMapper : BaseMapper<SingleItem, StoredItem> {
    override fun mapToPresentation(from: StoredItem) = SingleItem(
        id = from.id,
        title = from.name,
        description = from.description,
        tags = from.tags,
        isUsed = from.isUsed
    )

    override fun mapToDomain(from: SingleItem) = StoredItem(
        id = from.id,
        name = from.title,
        description = from.description ?: "",
        tags = from.tags,
        isUsed = from.isUsed
    )

}