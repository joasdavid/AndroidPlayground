package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.domain.data.ItemDetail
import com.joasvpereira.main.presentation.icons.DivisionIcon
import com.joasvpereira.main.presentation.icons.DivisionIcons
import com.joasvpereira.main.repository.BoxDataSource
import com.joasvpereira.main.repository.DivisionDataSource
import com.joasvpereira.main.repository.ItemDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.Params
import pt.joasvpereira.coreui.ThemeOption

data class GetItemDetailsParams(
    val id: Int
) : Params()

interface IGetItemDetailsUseCase : BaseUseCaseSync<GetItemDetailsParams, Flow<ItemDetail>>

class GetItemDetailsUseCase(
    private val boxDataSource: BoxDataSource,
    private val itemDataSource: ItemDataSource,
    private val divisionDataSource: DivisionDataSource
) : IGetItemDetailsUseCase {
    override suspend fun execute(params: GetItemDetailsParams): Flow<ItemDetail> =
        itemDataSource.getItem(params.id).map {
            val division = divisionDataSource.getDivision(it.parentDivisionId)
            ItemDetail(
                id = it.id!!,
                name = it.name,
                description = it.description,
                parentDivision = ItemDetail.ParentDivision(
                    id = division?.id ?: 0,
                    name = division?.name ?: "",
                    divisionIcon = if (division != null) DivisionIcons.getBy(division.iconId) else null,
                    themeOption = if (division != null) ThemeOption.getBy(division.themeId) else null,
                ),
                parentBox = null
            )
        }


}