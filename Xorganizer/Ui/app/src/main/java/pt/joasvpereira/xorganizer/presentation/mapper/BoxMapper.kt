package pt.joasvpereira.xorganizer.presentation.mapper

import pt.joasvpereira.xorganizer.domain.model.Box
import pt.joasvpereira.xorganizer.presentation.compose.division.SingleBox

class BoxMapper: BaseMapper<SingleBox, Box> {
    override fun mapToPresentation(from: Box) = SingleBox(
        id = from.id,
        title = from.name,
        description = from.description,
        nrItems = 0
    )

    override fun mapToDomain(from: SingleBox) = Box(
        id = from.id,
        name = from.title,
        description = from.description ?: ""
    )
}