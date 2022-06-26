package pt.joasvpereira.xorganizer.domain.usecase.box

import kotlinx.coroutines.flow.Flow
import pt.joasvpereira.xorganizer.domain.model.Box
import pt.joasvpereira.xorganizer.domain.repo.BoxDataSource
import pt.joasvpereira.xorganizer.domain.repo.FromDivision
import pt.joasvpereira.xorganizer.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.xorganizer.domain.usecase.Params

data class SourceDivision(val id: Int): Params()

interface IBoxesUseCase : BaseUseCaseSync<SourceDivision?, Flow<List<Box>>>

class BoxesUseCase(private val boxesDataSource: BoxDataSource) : IBoxesUseCase {
    override suspend fun execute(params: SourceDivision?): Flow<List<Box>> = boxesDataSource.getBoxes(FromDivision(params!!.id))
}