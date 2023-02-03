package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.domain.data.DivisionElement
import com.joasvpereira.main.domain.data.DivisionElements
import com.joasvpereira.main.repository.BoxDataSource
import com.joasvpereira.main.repository.ItemDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import pt.joasvpereira.core.repository.local.entities.Box
import pt.joasvpereira.core.repository.local.entities.Item

class GetDivisionElementsUseCase(
    private val boxDataSource: BoxDataSource,
    private val itemDataSource: ItemDataSource
) : IGetDivisionElementsUseCase {
    override suspend fun execute(params: DivisionIdParam): Flow<DivisionElements> =
        boxDataSource.getBoxes(divisionId = params.id).combine(itemDataSource.getDivisionItems(params.id)) { boxList: List<Box>, itemList: List<Item> ->
            val list = boxList.map {
                DivisionElement.Box(id = it.id!!, name = it.name)
            }.plus(
                itemList.map {
                    DivisionElement.Item(id = it.id!!, name = it.name)
                }
            )
            DivisionElements(
                list = list,
                nrBoxes = boxList.size,
                nrItems = itemList.size
            )
        }

}