package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.domain.data.DashboardDivision
import com.joasvpereira.main.presentation.icons.DivisionIcons
import com.joasvpereira.main.repository.DivisionDataSource
import pt.joasvpereira.core.domain.usecase.EmptyParams
import pt.joasvpereira.coreui.ThemeOption

class MyDivisionsUseCase(private val dataSource: DivisionDataSource) : IDivisionsUseCase {
    override suspend fun execute(params: EmptyParams): List<DashboardDivision> {
        return dataSource.getDivisions().map {
            DashboardDivision(
                id = it.id!!,
                name = it.name,
                description = it.description,
                icon = DivisionIcons.bathtub,//DivisionIcons.getBy(it.iconId)!!,
                boxCount = 0,
                itemCount = 0,
                themeOption = ThemeOption.values()[it.themeId]
            )
        }
    }
}