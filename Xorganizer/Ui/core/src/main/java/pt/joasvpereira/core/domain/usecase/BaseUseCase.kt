package pt.joasvpereira.core.domain.usecase

interface BaseUseCase<INPUT, OUTPUT> {
    fun execute(params: Params) : OUTPUT
}

interface BaseUseCaseSync<INPUT : Params?, OUTPUT> {
    suspend fun execute(params: INPUT) : OUTPUT
}

// TODO: this can be a simple interface
abstract class Params()

class EmptyParams: Params()