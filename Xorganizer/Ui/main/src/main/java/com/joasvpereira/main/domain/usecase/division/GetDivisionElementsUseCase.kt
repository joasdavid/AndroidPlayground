package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.domain.data.DivisionElement
import com.joasvpereira.main.domain.data.DivisionElements
import com.joasvpereira.main.repository.BoxDataSource
import com.joasvpereira.main.repository.ItemDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import pt.joasvpereira.core.repository.local.entities.Box
import pt.joasvpereira.core.repository.local.entities.Item

class GetDivisionElementsUseCase(
    private val boxDataSource: BoxDataSource,
    private val itemDataSource: ItemDataSource
) : IGetDivisionElementsUseCase {
    override suspend fun execute(params: GetDivisionElementsParams): Flow<DivisionElements> = filter(params)

    private suspend fun onlyBoxes(divisionId: Int): Flow<List<Box>> = boxDataSource.getBoxes(divisionId = divisionId)
    private suspend fun onlyItems(divisionId: Int): Flow<List<Item>> = itemDataSource.getDivisionItems(divisionId)
    private suspend fun filter(params: GetDivisionElementsParams): Flow<DivisionElements> = onlyBoxes(params.divisionId)
        .combine(onlyItems(params.divisionId)) { boxList: List<Box>, itemList: List<Item> ->
            val list = when (params.filter) {
                GetDivisionElementsParams.Filter.All -> boxList.map {
                    it.mapToDivisionElement()
                }.plus(
                    itemList.map { it.mapToDivisionElement() }
                )

                GetDivisionElementsParams.Filter.OnlyBoxes -> boxList.map { it.mapToDivisionElement() }
                GetDivisionElementsParams.Filter.OnlyItems -> itemList.map { it.mapToDivisionElement() }
            }

            DivisionElements(list = list, nrBoxes = boxList.size, nrItems = itemList.size)
        }

    private fun Box.mapToDivisionElement() = DivisionElement.Box(
        id = id!!,
        name = name,
        description = description
    )

    private fun Item.mapToDivisionElement() = DivisionElement.Item(
        id = id!!,
        name = name,
        description = description
    )

}