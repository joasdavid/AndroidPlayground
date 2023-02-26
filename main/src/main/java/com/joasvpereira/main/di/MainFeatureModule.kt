package com.joasvpereira.main.di

import com.joasvpereira.loggger.extentions.logThis
import com.joasvpereira.main.domain.usecase.division.CreateBoxUseCase
import com.joasvpereira.main.domain.usecase.division.CreateDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.CreateItemUseCase
import com.joasvpereira.main.domain.usecase.division.DeleteBoxUseCase
import com.joasvpereira.main.domain.usecase.division.DeleteDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.DeleteItemUseCase
import com.joasvpereira.main.domain.usecase.division.DivisionUseCase
import com.joasvpereira.main.domain.usecase.division.GetBoxElementsUseCase
import com.joasvpereira.main.domain.usecase.division.GetBoxUseCase
import com.joasvpereira.main.domain.usecase.division.GetDivisionElementsUseCase
import com.joasvpereira.main.domain.usecase.division.GetItemDetailsUseCase
import com.joasvpereira.main.domain.usecase.division.ICreateBoxUseCase
import com.joasvpereira.main.domain.usecase.division.ICreateDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.ICreateItemUseCase
import com.joasvpereira.main.domain.usecase.division.IDeleteBoxUseCase
import com.joasvpereira.main.domain.usecase.division.IDeleteDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.IDeleteItemUseCase
import com.joasvpereira.main.domain.usecase.division.IDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.IDivisionsUseCase
import com.joasvpereira.main.domain.usecase.division.IGetBoxElementsUseCase
import com.joasvpereira.main.domain.usecase.division.IGetBoxUseCase
import com.joasvpereira.main.domain.usecase.division.IGetDivisionElementsUseCase
import com.joasvpereira.main.domain.usecase.division.IGetItemDetailsUseCase
import com.joasvpereira.main.domain.usecase.division.IUpdateBoxUseCase
import com.joasvpereira.main.domain.usecase.division.IUpdateDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.IUpdateItemUseCase
import com.joasvpereira.main.domain.usecase.division.MyDivisionsUseCase
import com.joasvpereira.main.domain.usecase.division.UpdateBoxUseCase
import com.joasvpereira.main.domain.usecase.division.UpdateDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.UpdateItemUseCase
import com.joasvpereira.main.presentation.box.BoxScreenViewModel
import com.joasvpereira.main.presentation.create.CreateDivisionViewModel
import com.joasvpereira.main.presentation.dashboard.DashboardFeatureScreenViewModel
import com.joasvpereira.main.presentation.details.ItemDetailScreenViewModel
import com.joasvpereira.main.presentation.division.DivisionsFeatureViewModel
import com.joasvpereira.main.repository.BoxDataSource
import com.joasvpereira.main.repository.DivisionDataSource
import com.joasvpereira.main.repository.ItemDataSource
import com.joasvpereira.main.repository.LocalBoxDataSource
import com.joasvpereira.main.repository.LocalDivisionDataSource
import com.joasvpereira.main.repository.LocalItemDataSource
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import pt.joasvpereira.core.repository.local.Db

val MainFeatureModule = module {
    viewModel {
        DashboardFeatureScreenViewModel(
            divisionsUseCase = get(),
            deleteUseCase = get(),
            getSessionUseCase = get(),
        )
    }

    viewModel {
        CreateDivisionViewModel(
            divisionUseCase = get(),
            createDivisionUseCase = get(),
            updateDivisionUseCase = get(),
        )
    }

    viewModel { (divisionId: Int) ->
        DivisionsFeatureViewModel(
            divisionId = divisionId,
            divisionUseCase = get(),
            getDivisionElementsUseCase = get(),
            createBoxUseCase = get(),
            createItemUseCase = get(),
            deleteBoxUseCase = get(),
            deleteItemUseCase = get(),
            updateItemUseCase = get(),
            updateBoxUseCase = get(),
        )
    }

    viewModel { (itemId: Int) ->
        ItemDetailScreenViewModel(
            itemId = itemId,
            getDetails = get(),
            updateItemUseCase = get(),
            deleteItemUseCase = get(),
        )
    }

    viewModel { (boxId: Int) ->
        BoxScreenViewModel(
            boxId = boxId,
            getBoxUseCase = get(),
            getBoxElementsUseCase = get(),
            createItemUseCase = get(),
            deleteItemUseCase = get(),
            updateItemUseCase = get(),
        )
    }

    single<IDivisionUseCase> { DivisionUseCase(get()) }

    single<IDeleteBoxUseCase> { DeleteBoxUseCase(get()) }

    single<IDeleteItemUseCase> { DeleteItemUseCase(get()) }

    single<IUpdateItemUseCase> { UpdateItemUseCase(get()) }

    single<IUpdateBoxUseCase> { UpdateBoxUseCase(get()) }

    single<IGetItemDetailsUseCase> {
        GetItemDetailsUseCase(
            boxDataSource = get(),
            itemDataSource = get(),
            divisionDataSource = get(),
        )
    }

    factory<ICreateDivisionUseCase> {
        CreateDivisionUseCase(
            dataSource = get(),
            sessionId = get(named("SESSION_ID")),
        )
    }

    factory<IUpdateDivisionUseCase> {
        UpdateDivisionUseCase(
            dataSource = get(),
            sessionId = get(named("SESSION_ID")),
        )
    }

    factory<IDivisionsUseCase> {
        MyDivisionsUseCase(
            dataSource = get(),
            boxDataSource = get(),
            itemDataSource = get(),
        )
    }

    factory<IDeleteDivisionUseCase> {
        DeleteDivisionUseCase(get())
    }

    single<IGetDivisionElementsUseCase> {
        GetDivisionElementsUseCase(
            boxDataSource = get(),
            itemDataSource = get(),
        )
    }

    single<ICreateBoxUseCase> {
        CreateBoxUseCase(
            boxDataSource = get(),
        )
    }

    single<ICreateItemUseCase> {
        CreateItemUseCase(
            itemDataSource = get(),
        )
    }

    single<IGetBoxUseCase> {
        GetBoxUseCase(
            boxDataSource = get(),
            divisionDataSource = get(),
        )
    }

    single<IGetBoxElementsUseCase> {
        GetBoxElementsUseCase(
            itemDataSource = get(),
        )
    }

    factory<DivisionDataSource> {
        "build DivisionDataSource with id = ${get<Int>(named("SESSION_ID"))}".logThis()
        LocalDivisionDataSource(get(named("SESSION_ID")), get<Db>().userDao())
    }

    single<BoxDataSource> { LocalBoxDataSource(get<Db>().boxDao()) }
    single<ItemDataSource> { LocalItemDataSource(get<Db>().itemDao()) }
}
