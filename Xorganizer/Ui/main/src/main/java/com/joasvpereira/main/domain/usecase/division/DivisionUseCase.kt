package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.domain.data.DashboardDivision
import com.joasvpereira.main.domain.data.DivisionThemed
import com.joasvpereira.main.presentation.icons.DivisionIcon
import com.joasvpereira.main.presentation.icons.DivisionIcons
import com.joasvpereira.main.repository.DivisionDataSource
import pt.joasvpereira.core.domain.usecase.EmptyParams
import pt.joasvpereira.coreui.ThemeOption

class DivisionUseCase(private val dataSource: DivisionDataSource) : IDivisionUseCase {
    override suspend fun execute(params: DivisionIdParam): DivisionThemed {
        return requireNotNull(dataSource.getDivision(params.id)) { "Division not found" }.run {
            DivisionThemed(
                id = id!!,
                name = name,
                description = description,
                icon = runCatching { DivisionIcons.getBy(iconId)!! }.getOrDefault(DivisionIcons.home),
                themeOption = ThemeOption.getBy(themeId)
            )
        }
    }
}