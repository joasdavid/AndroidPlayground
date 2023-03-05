package pt.joasvpereira.xorganizer.di

import androidx.room.Room
import androidx.room.RoomDatabase.Callback
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import pt.joasvpereira.core.repository.CurrentSession
import pt.joasvpereira.core.repository.local.Db
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
import pt.joasvpereira.xorganizer.presentation.MainViewModel

val viewModelModule = module {

    viewModel {
        MainViewModel(get())
    }

    factory<Int?>(named("SESSION_ID")) {
        CurrentSession.sessionId
    }
}

val usecases = module {
    factory<ISingleDivisionUseCase> { SingleDivisionUseCase(divisionDataSource = get()) }
    factory<IDivisionsUseCase> { DivisionsUseCase(divisionDataSource = get()) }
    factory<ICreateDivisionsUseCase> { CreateDivisionsUseCase(divisionDataSource = get()) }
    factory<IBoxesUseCase> { BoxesUseCase(boxesDataSource = get()) }
    factory<IItemsUseCase> { ItemsUseCase(storedItemDataSource = get()) }
}

val repository = module {
    single<Db> {
        val v = Room.databaseBuilder(
            androidApplication(),
            Db::class.java,
            "database-name",
        ).addCallback(object : Callback() {}).build()
        v
    }
}
