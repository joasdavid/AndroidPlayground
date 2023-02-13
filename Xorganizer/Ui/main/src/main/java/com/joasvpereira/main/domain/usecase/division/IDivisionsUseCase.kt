package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.main.domain.data.DashboardDivision
import kotlinx.coroutines.flow.Flow
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.EmptyParams

interface IDivisionsUseCase : BaseUseCaseSync<EmptyParams, Flow<List<DashboardDivision>>>