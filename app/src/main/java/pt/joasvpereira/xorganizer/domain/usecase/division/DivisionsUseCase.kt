package pt.joasvpereira.xorganizer.domain.usecase.division

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.Params
import pt.joasvpereira.xorganizer.domain.model.Division
import pt.joasvpereira.xorganizer.domain.repo.DivisionDataSource

interface IDivisionsUseCase : BaseUseCaseSync<Params?, List<Division>>

class DivisionsUseCase(private val divisionDataSource: DivisionDataSource) : IDivisionsUseCase {
    override suspend fun execute(params: Params?): List<Division> = withContext(Dispatchers.IO) {
        divisionDataSource.getDivisions()
    }
}
