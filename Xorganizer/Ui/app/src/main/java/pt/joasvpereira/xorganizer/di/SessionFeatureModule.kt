package pt.joasvpereira.xorganizer.di

import androidx.room.Room
import androidx.room.RoomDatabase.Callback
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pt.joasvpereira.core.domain.usecase.EmptyParams
import pt.joasvpereira.xorganizer.domain.model.Division
import pt.joasvpereira.xorganizer.domain.repo.DivisionDataSource
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
import pt.joasvpereira.sessionfeature.domain.usecase.ISessionUseCase
import pt.joasvpereira.sessionfeature.domain.usecase.SessionUseCase
import pt.joasvpereira.xorganizer.presentation.compose.MainScreenViewModel
import pt.joasvpereira.xorganizer.presentation.compose.common.add.CreateFolderViewModel
import pt.joasvpereira.xorganizer.presentation.compose.division.DivisionScreenViewModel
import pt.joasvpereira.xorganizer.presentation.compose.item.ItemScreenViewModel
import pt.joasvpereira.xorganizer.presentation.compose.item.Mode
import pt.joasvpereira.xorganizer.presentation.mapper.DivisionsMapper
import pt.joasvpereira.core.repository.local.Db
import pt.joasvpereira.core.repository.local.entities.Session
import pt.joasvpereira.sessionfeature.domain.data.SessionItem
import pt.joasvpereira.sessionfeature.domain.usecase.ISessionsUseCase
import pt.joasvpereira.sessionfeature.domain.usecase.SessionsUseCase
import pt.joasvpereira.sessionfeature.presentation.select.session.SelectSessionViewModel
import pt.joasvpereira.sessionfeature.repository.LocalSessionDataSource

val sessionFeatureModule = module {
    viewModel {
        SelectSessionViewModel(get())
    }

    single<ISessionsUseCase> {
        SessionsUseCase(LocalSessionDataSource(get<Db>().sessionDao()))
    }
}
