package pt.joasvpereira.xorganizer.domain.usecase.item

import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.xorganizer.domain.model.StoredItem
import pt.joasvpereira.xorganizer.domain.repo.FromDivision
import pt.joasvpereira.xorganizer.domain.repo.StoredItemDataSource
import pt.joasvpereira.xorganizer.domain.usecase.box.SourceDivision

interface IItemsUseCase : BaseUseCaseSync<SourceDivision?, List<StoredItem>>

class ItemsUseCase(private val storedItemDataSource: StoredItemDataSource) : IItemsUseCase {
    override suspend fun execute(params: SourceDivision?): List<StoredItem> =
        storedItemDataSource.getItems(FromDivision(params!!.id))
}
