package pt.joasvpereira.core.domain.usecase

@Suppress("unused")
interface BaseUseCase<INPUT : Params?, OUTPUT> {
    fun execute(params: INPUT): OUTPUT
}

interface BaseUseCaseSync<INPUT : Params?, OUTPUT> {
    suspend fun execute(params: INPUT): OUTPUT
}

interface Params

class EmptyParams : Params
