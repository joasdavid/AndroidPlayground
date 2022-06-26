package pt.joasvpereira.xorganizer.domain.usecase.division

import pt.joasvpereira.xorganizer.domain.model.Division
import pt.joasvpereira.xorganizer.domain.repo.DivisionDataSource
import pt.joasvpereira.xorganizer.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.xorganizer.domain.usecase.Params

data class DivisionId(val id: Int) : Params()

interface ISingleDivisionUseCase : BaseUseCaseSync<DivisionId, Division?>

class SingleDivisionUseCase(private val divisionDataSource: DivisionDataSource) : ISingleDivisionUseCase {
    override suspend fun execute(params: DivisionId): Division? =
        kotlin.runCatching { divisionDataSource.singleDivisions(params.id) }.getOrNull()
}