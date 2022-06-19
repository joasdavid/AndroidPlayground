package pt.joasvpereira.xorganizer.domain.usecase.division

import pt.joasvpereira.xorganizer.domain.model.Division
import pt.joasvpereira.xorganizer.domain.repo.DivisionDataSource
import pt.joasvpereira.xorganizer.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.xorganizer.domain.usecase.EmptyParams
import pt.joasvpereira.xorganizer.domain.usecase.Params

interface IDivisionsUseCase: BaseUseCaseSync<Params?, List<Division>>

class DivisionsUseCase(private val divisionDataSource: DivisionDataSource): IDivisionsUseCase {
    override suspend fun execute(params: Params?): List<Division> = divisionDataSource.getDivisions()
}