package pt.joasvpereira.xorganizer.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pt.joasvpereira.xorganizer.domain.repo.DivisionDataSource
import pt.joasvpereira.xorganizer.domain.usecase.division.DivisionsUseCase
import pt.joasvpereira.xorganizer.domain.usecase.division.IDivisionsUseCase
import pt.joasvpereira.xorganizer.domain.usecase.division.ISingleDivisionUseCase
import pt.joasvpereira.xorganizer.domain.usecase.division.SingleDivisionUseCase
import pt.joasvpereira.xorganizer.presentation.compose.MainScreenViewModel
import pt.joasvpereira.xorganizer.presentation.compose.common.add.CreateFolderViewModel
import pt.joasvpereira.xorganizer.presentation.compose.division.DivisionScreenViewModel
import pt.joasvpereira.xorganizer.presentation.compose.item.ItemScreenViewModel
import pt.joasvpereira.xorganizer.presentation.compose.item.Mode
import pt.joasvpereira.xorganizer.presentation.mapper.DivisionsMapper
import pt.joasvpereira.xorganizer.repository.InMemory

val viewModelModule = module {

    viewModel {
        MainScreenViewModel(divisionUseCase = get(), mapper = DivisionsMapper())
    }

    viewModel { (id: Int) ->
        DivisionScreenViewModel(id = id, singleDivisionUseCase = get())
    }

    viewModel { (id: Int, mode: Mode) ->
        ItemScreenViewModel(id = id, mode = mode)
    }

    viewModel {
        CreateFolderViewModel()
    }
}

val usecases = module {
    factory<ISingleDivisionUseCase> { SingleDivisionUseCase(divisionDataSource = get()) }
    factory<IDivisionsUseCase> { DivisionsUseCase(divisionDataSource = get()) }
}

val repository = module {
    factory<DivisionDataSource> { InMemory() }
}