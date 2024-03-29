package pt.joasvpereira.xorganizer.domain.usecase.box

import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.Params
import pt.joasvpereira.xorganizer.domain.model.Box
import pt.joasvpereira.xorganizer.domain.repo.BoxDataSource
import pt.joasvpereira.xorganizer.domain.repo.FromDivision

data class SourceDivision(val id: Int) : Params

interface IBoxesUseCase : BaseUseCaseSync<SourceDivision?, List<Box>>

class BoxesUseCase(private val boxesDataSource: BoxDataSource) : IBoxesUseCase {
    override suspend fun execute(params: SourceDivision?): List<Box> =
        boxesDataSource.getBoxes(FromDivision(params!!.id))
}
