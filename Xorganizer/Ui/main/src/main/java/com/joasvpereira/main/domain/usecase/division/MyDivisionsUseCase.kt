package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.domain.data.DashboardDivision
import com.joasvpereira.main.presentation.icons.DivisionIcons
import com.joasvpereira.main.repository.BoxDataSource
import com.joasvpereira.main.repository.DivisionDataSource
import com.joasvpereira.main.repository.ItemDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import pt.joasvpereira.core.domain.usecase.EmptyParams
import pt.joasvpereira.core.repository.local.entities.BoxCountAndParentId
import pt.joasvpereira.core.repository.local.entities.Division
import pt.joasvpereira.core.repository.local.entities.ItemCountAndParentId
import pt.joasvpereira.coreui.ThemeOption

class MyDivisionsUseCase(
    private val dataSource: DivisionDataSource,
    private val boxDataSource: BoxDataSource,
    private val itemDataSource: ItemDataSource
) : IDivisionsUseCase {
    override suspend fun execute(params: EmptyParams): Flow<List<DashboardDivision>> {
        val divisionsFlow = dataSource.getDivisions()
        val boxesCount = boxDataSource.countBoxFrom()
        val itemsCount = itemDataSource.getItemCount()
        return combine(
            divisionsFlow,
            boxesCount,
            itemsCount,
            transform = { divisions: List<Division>, boxCountAndParentIds: List<BoxCountAndParentId>, itemCountAndParentIds: List<ItemCountAndParentId> ->
                divisions.map {
                    DashboardDivision(
                        id = it.id!!,
                        name = it.name,
                        description = it.description,
                        icon = DivisionIcons.getBy(it.iconId)!!,
                        boxCount = boxCountAndParentIds.find { singleCount -> singleCount.parentDivisionId == it.id!! }?.count ?: 0,
                        itemCount = itemCountAndParentIds.find { singleCount -> singleCount.parentDivisionId == it.id!! }?.count ?: 0,
                        themeOption = ThemeOption.values()[it.themeId]
                    )
                }
            }
        )
    }
}