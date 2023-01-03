package pt.joasvpereira.xorganizer.di

import androidx.room.Room
import androidx.room.RoomDatabase.Callback
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
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
import pt.joasvpereira.sessionfeature.CurrentSession

val viewModelModule = module {

    viewModel {
        MainScreenViewModel(
            divisionUseCase = get(),
            mapper = DivisionsMapper()
        )
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
    factory<IBoxesUseCase> { BoxesUseCase(boxesDataSource = get()) }
    factory<IItemsUseCase> { ItemsUseCase(storedItemDataSource = get()) }
}

// TODO: refactor this, a lot of this need to go to the repo
val repository = module {
    single<Db> {
        val v = Room.databaseBuilder(
            androidApplication(),
            Db::class.java,
            "database-name"
        ).addCallback(object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

            }
        }).build()
       v
    }
    single<DivisionDataSource> {
        //repo
        val db: Db = get()
        object : DivisionDataSource {
            override suspend fun getDivisions(): List<Division> {
                return db.userDao().getAll(fromSessionId = CurrentSession.session?.id ?: 0).map {
                    Division(
                        id = it.id!!,
                        name = it.name,
                        description = it.description,
                        iconId = it.iconId,
                        nrBox = 0,
                        nrItem = 0,
                        themeId = it.themeId
                    )
                }
            }

            override suspend fun singleDivisions(id: Int): Division {
                return db.userDao().getDivision(id).let {
                    Division(
                        id = it.id!!,
                        name = it.name,
                        description = it.description,
                        iconId = it.iconId,
                        nrBox = 0,
                        nrItem = 0,
                        themeId = it.themeId
                    )
                }
            }

            override suspend fun addDivision(division: Division) {
                db.userDao().insertDivision(division = division.let {
                    pt.joasvpereira.core.repository.local.entities.Division(
                        id = null,
                        name = it.name,
                        description = it.description,
                        iconId = it.iconId,
                        themeId = it.themeId,
                        sessionId = 1
                    )
                })
            }
        }
    }
}
