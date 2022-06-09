package pt.joasvpereira.xorganizer.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pt.joasvpereira.xorganizer.compose.common.add.CreateFolderBottomSheetState
import pt.joasvpereira.xorganizer.compose.common.add.CreateFolderContentState
import pt.joasvpereira.xorganizer.compose.common.add.CreateFolderViewModel
import pt.joasvpereira.xorganizer.compose.division.DivisionScreenViewModel
import pt.joasvpereira.xorganizer.compose.item.ItemScreenViewModel
import pt.joasvpereira.xorganizer.compose.item.Mode

val viewModelModule = module {
    viewModel { (id: Int) ->
        DivisionScreenViewModel(id)
    }

    viewModel { (id: Int, mode: Mode) ->
        ItemScreenViewModel(id, mode)
    }

    viewModel {
        CreateFolderViewModel()
    }
}