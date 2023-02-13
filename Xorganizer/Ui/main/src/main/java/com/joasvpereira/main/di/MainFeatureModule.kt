package com.joasvpereira.main.di

import android.graphics.Bitmap
import android.graphics.ColorSpace.Named
import com.joasvpereira.main.domain.usecase.division.CreateBoxUseCase
import com.joasvpereira.main.domain.usecase.division.CreateDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.CreateItemUseCase
import com.joasvpereira.main.domain.usecase.division.DeleteBoxUseCase
import com.joasvpereira.main.domain.usecase.division.DeleteDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.DeleteItemUseCase
import com.joasvpereira.main.domain.usecase.division.DivisionUseCase
import com.joasvpereira.main.domain.usecase.division.GetDivisionElementsUseCase
import com.joasvpereira.main.domain.usecase.division.ICreateBoxUseCase
import com.joasvpereira.main.domain.usecase.division.ICreateDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.ICreateItemUseCase
import com.joasvpereira.main.domain.usecase.division.IDeleteBoxUseCase
import com.joasvpereira.main.domain.usecase.division.IDeleteDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.IDeleteItemUseCase
import com.joasvpereira.main.domain.usecase.division.IDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.IDivisionsUseCase
import com.joasvpereira.main.domain.usecase.division.IGetDivisionElementsUseCase
import com.joasvpereira.main.domain.usecase.division.IUpdateBoxUseCase
import com.joasvpereira.main.domain.usecase.division.IUpdateDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.IUpdateItemUseCase
import com.joasvpereira.main.domain.usecase.division.MyDivisionsUseCase
import com.joasvpereira.main.domain.usecase.division.UpdateBoxUseCase
import com.joasvpereira.main.domain.usecase.division.UpdateDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.UpdateItemUseCase
import com.joasvpereira.main.presentation.create.CreateDivisionViewModel
import com.joasvpereira.main.presentation.dashboard.DashboardFeatureScreenViewModel
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
            sessionName = get(named("SESSION_NAME")),
            sessionImage = kotlin.runCatching { get<Bitmap>(named("SESSION_IMAGE")) }.getOrNull(),
            divisionsUseCase = get(),
            deleteUseCase = get(),
        )
    }

    viewModel {
        CreateDivisionViewModel(
            divisionUseCase = get(),
            createDivisionUseCase = get(),
            updateDivisionUseCase = get()
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

    single<IDivisionUseCase> { DivisionUseCase(get()) }

    single<IDeleteBoxUseCase> { DeleteBoxUseCase(get()) }

    single<IDeleteItemUseCase> { DeleteItemUseCase(get()) }

    single<IUpdateItemUseCase> { UpdateItemUseCase(get()) }

    single<IUpdateBoxUseCase> { UpdateBoxUseCase(get()) }

    factory<ICreateDivisionUseCase> {
        CreateDivisionUseCase(
            dataSource = get(),
            sessionId = get(named("SESSION_ID"))
        )
    }

    factory<IUpdateDivisionUseCase> {
        UpdateDivisionUseCase(
            dataSource = get(),
            sessionId = get(named("SESSION_ID"))
        )
    }

    factory<IDivisionsUseCase> {
        MyDivisionsUseCase(
            dataSource = get(),
            boxDataSource = get(),
            itemDataSource = get()
        )
    }

    factory<IDeleteDivisionUseCase> {
        DeleteDivisionUseCase(get())
    }

    single<IGetDivisionElementsUseCase> {
        GetDivisionElementsUseCase(
            boxDataSource = get(),
            itemDataSource = get()
        )
    }

    single<ICreateBoxUseCase> {
        CreateBoxUseCase(
            boxDataSource = get()
        )
    }

    single<ICreateItemUseCase> {
        CreateItemUseCase(
            itemDataSource = get()
        )
    }

    factory<DivisionDataSource> { LocalDivisionDataSource(get(named("SESSION_ID")), get<Db>().userDao()) }

    single<BoxDataSource> { LocalBoxDataSource(get<Db>().boxDao()) }
    single<ItemDataSource> { LocalItemDataSource(get<Db>().itemDao()) }
}