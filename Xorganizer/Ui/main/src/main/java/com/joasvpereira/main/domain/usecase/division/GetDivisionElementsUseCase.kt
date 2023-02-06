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
                    DivisionElement.Box(id = it.id!!, name = it.name)
                }.plus(
                    itemList.map {
                        DivisionElement.Item(id = it.id!!, name = it.name)
                    }
                )

                GetDivisionElementsParams.Filter.OnlyBoxes -> boxList.map { DivisionElement.Box(id = it.id!!, name = it.name) }
                GetDivisionElementsParams.Filter.OnlyItems -> itemList.map { DivisionElement.Item(id = it.id!!, name = it.name) }
            }

            DivisionElements(list = list, nrBoxes = boxList.size, nrItems = itemList.size)
        }

}