package pt.joasvpereira.xorganizer.di

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pt.joasvpereira.xorganizer.domain.model.Division
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
import pt.joasvpereira.xorganizer.repository.local.Db
import pt.joasvpereira.xorganizer.repository.local.entities.Division as DivisionDb

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
    factory<IBoxesUseCase> { BoxesUseCase(boxesDataSource = get()) }
    factory<IItemsUseCase> { ItemsUseCase(storedItemDataSource = get()) }
}

// TODO: refactor this, a lot of this need to go to the repo
val repository = module {
    single {
        val v = Room.databaseBuilder(
            androidApplication(),
            Db::class.java,
            "database-name"
        ).build()
        v
    }
    single<DivisionDataSource> {
        //repo
        val db: Db = get()
        object : DivisionDataSource {
            override suspend fun getDivisions(): Flow<List<Division>> = db.userDao().getAll().map {
                val l = mutableListOf<Division>()
                it.forEach {
                    l.add(
                    Division(
                        id = it.id ?: 0,
                        name = it.name,
                        description = it.description,
                        iconId = it.iconId,
                        nrBox = 0,
                        nrItem = 0,
                        themeId = it.themeId
                    )
                    )
                }
                l
            }

            override suspend fun singleDivisions(id: Int) = with(db.userDao().getDivision(id)) {
                Division(
                    id = this.id ?: 0,
                    name = this.name,
                    description = this.description,
                    iconId = this.iconId,
                    nrBox = 0,
                    nrItem = 0,
                    themeId = this.themeId
                )
            }

            override suspend fun addDivision(division: Division) {
                get<Db>().userDao().insertDivision(
                    DivisionDb(
                        name = division.name,
                        description = division.description,
                        iconId = division.iconId,
                        themeId = division.themeId
                    )
                )
            }
        }
    }
    single<BoxDataSource> { repo }
    single<StoredItemDataSource> { repo }
}

val repo = InMemory()