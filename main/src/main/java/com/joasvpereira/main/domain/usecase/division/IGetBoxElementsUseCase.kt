package com.joasvpereira.main.domain.usecase.division

import com.joasvpereira.loggger.extentions.logThis
import com.joasvpereira.main.domain.data.DivisionElement
import com.joasvpereira.main.repository.ItemDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.domain.usecase.BaseUseCaseSync
import pt.joasvpereira.core.domain.usecase.Params

data class GetBoxElementsParams(
    val boxId: Int,
) : Params

interface IGetBoxElementsUseCase : BaseUseCaseSync<GetBoxElementsParams, Flow<List<DivisionElement.Item>>>

class GetBoxElementsUseCase(
    private val itemDataSource: ItemDataSource,
) : IGetBoxElementsUseCase {
    override suspend fun execute(params: GetBoxElementsParams): Flow<List<DivisionElement.Item>> = withContext(Dispatchers.IO) {
        itemDataSource.getBoxItems(params.boxId).map {
            it.logThis(tag = "JVP") {
                """
                    List of items size = ${it.size}
                """.trimIndent()
            }
            it.map { listItem ->
                DivisionElement.Item(id = listItem.id!!, name = listItem.name, description = listItem.description)
            }
        }
    }
}
