package pt.joasvpereira.xorganizer.domain.usecase.division

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import pt.joasvpereira.xorganizer.domain.model.Division
import pt.joasvpereira.xorganizer.domain.repo.DivisionDataSource
import pt.joasvpereira.xorganizer.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.xorganizer.domain.usecase.Params

interface IDivisionsUseCase : BaseUseCaseSync<Params?, Flow<List<Division>>>

class DivisionsUseCase(private val divisionDataSource: DivisionDataSource) : IDivisionsUseCase {
    override suspend fun execute(params: Params?): Flow<List<Division>> = withContext(Dispatchers.IO) {
        divisionDataSource.getDivisions()
    }
}