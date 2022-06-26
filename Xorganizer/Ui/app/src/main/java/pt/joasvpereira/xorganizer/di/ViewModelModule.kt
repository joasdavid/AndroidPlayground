package pt.joasvpereira.xorganizer.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pt.joasvpereira.xorganizer.domain.repo.BoxDataSource
import pt.joasvpereira.xorganizer.domain.repo.DivisionDataSource
import pt.joasvpereira.xorganizer.domain.repo.StoredItemDataSource
import pt.joasvpereira.xorganizer.domain.usecase.box.BoxesUseCase
import pt.joasvpereira.xorganizer.domain.usecase.box.IBoxesUseCase
import pt.joasvpereira.xorganizer.domain.usecase.division.CreateDivisionsUseCase
import pt.joasvpereira.xorganizer.domain.usecase.division.DivisionsUseCase
import pt.joasvpereira.xorganizer.domain.usecase.division.ICreateDivisionsUseCase
import pt.joasvpereira.xorganizer.domain.usecase.division.IDivisionsUseCase
import pt.joasvpereira.xorganizer.domain.usecase.division.ISingleDivisionUseCase
import pt.joasvpereira.xorganizer.domain.usecase.division.SingleDivisionUseCase
import pt.joasvpereira.xorganizer.domain.usecase.item.IItemsUseCase
import pt.joasvpereira.xorganizer.domain.usecase.item.ItemsUseCase
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
        DivisionScreenViewModel(
            id = id,
            singleDivisionUseCase = get(),
            boxenUseCase = get(),
            itemsUseCase = get()
        )
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
    factory<ICreateDivisionsUseCase> { CreateDivisionsUseCase(divisionDataSource = get()) }
    factory<IBoxesUseCase> { BoxesUseCase( boxesDataSource = get()) }
    factory<IItemsUseCase> { ItemsUseCase(storedItemDataSource = get()) }
}

val repository = module {
    single<DivisionDataSource> { repo }
    single<BoxDataSource> { repo }
    single<StoredItemDataSource> { repo }
}

val repo = InMemory()