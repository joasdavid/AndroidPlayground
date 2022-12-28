package pt.joasvpereira.xorganizer.domain.usecase.division

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.joasvpereira.xorganizer.domain.model.Division
import pt.joasvpereira.xorganizer.domain.model.DivisionCreationInfo
import pt.joasvpereira.xorganizer.domain.repo.DivisionDataSource
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync

interface ICreateDivisionsUseCase: BaseUseCaseSync<DivisionCreationInfo, Unit>

class CreateDivisionsUseCase(private val divisionDataSource: DivisionDataSource): ICreateDivisionsUseCase {
    override suspend fun execute(params: DivisionCreationInfo) = withContext(Dispatchers.IO) {
        divisionDataSource.addDivision(
            Division(
                id = -1,
                name = params.name,
                description = params.description,
                iconId = 0,
                nrBox = 0,
                nrItem = 0,
                themeId = params.themeId
            )
        )
    }
}
