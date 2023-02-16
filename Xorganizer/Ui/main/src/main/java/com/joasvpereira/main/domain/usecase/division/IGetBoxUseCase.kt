package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.domain.data.ItemDetail
import com.joasvpereira.main.domain.data.ParentBox
import com.joasvpereira.main.presentation.icons.DivisionIcon
import com.joasvpereira.main.presentation.icons.DivisionIcons
import com.joasvpereira.main.repository.BoxDataSource
import com.joasvpereira.main.repository.DivisionDataSource
import com.joasvpereira.main.repository.ItemDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.Params
import pt.joasvpereira.coreui.ThemeOption

data class GetBoxParams(
    val id: Int
) : Params()

interface IGetBoxUseCase : BaseUseCaseSync<GetBoxParams, Flow<ParentBox?>>

class GetBoxUseCase(
    private val boxDataSource: BoxDataSource,
    private val divisionDataSource: DivisionDataSource
) : IGetBoxUseCase {
    override suspend fun execute(params: GetBoxParams): Flow<ParentBox?> = withContext(Dispatchers.IO) {
        boxDataSource.getBox(params.id).map {box ->
            if (box == null) return@map null
            ParentBox(
                id = box.id!!,
                name = box.name,
                description = box.description,
                parentDivision = divisionDataSource.getDivision(box.parentDivisionId)?.run {
                    ParentBox.ParentDivision(id = id!!, themeOption = ThemeOption.getBy(themeId))
                }
            )
        }
    }
}