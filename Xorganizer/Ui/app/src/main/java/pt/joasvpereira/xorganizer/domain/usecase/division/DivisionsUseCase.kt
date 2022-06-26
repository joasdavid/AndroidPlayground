package pt.joasvpereira.xorganizer.domain.usecase.division

import kotlinx.coroutines.flow.Flow
import pt.joasvpereira.xorganizer.domain.model.Division
import pt.joasvpereira.xorganizer.domain.repo.DivisionDataSource
import pt.joasvpereira.xorganizer.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.xorganizer.domain.usecase.EmptyParams
import pt.joasvpereira.xorganizer.domain.usecase.Params

interface IDivisionsUseCase: BaseUseCaseSync<Params?, Flow<List<Division>>>

class DivisionsUseCase(private val divisionDataSource: DivisionDataSource): IDivisionsUseCase {
    override suspend fun execute(params: Params?): Flow<List<Division>> = divisionDataSource.getDivisions()
}