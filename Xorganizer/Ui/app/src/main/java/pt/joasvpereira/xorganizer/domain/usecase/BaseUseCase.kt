package pt.joasvpereira.xorganizer.domain.usecase

interface BaseUseCase<INPUT, OUTPUT> {
    fun execute(params: Params) : OUTPUT
}

interface BaseUseCaseSync<INPUT : Params?, OUTPUT> {
    suspend fun execute(params: INPUT) : OUTPUT
}

abstract class Params()

class EmptyParams: Params()