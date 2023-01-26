package com.joasvpereira.main.di

import android.graphics.Bitmap
import com.joasvpereira.main.domain.usecase.division.IDivisionsUseCase
import com.joasvpereira.main.domain.usecase.division.MyDivisionsUseCase
import com.joasvpereira.main.presentation.dashboard.DashboardFeatureScreenViewModel
import com.joasvpereira.main.repository.DivisionDataSource
import com.joasvpereira.main.repository.LocalDivisionDataSource
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import pt.joasvpereira.core.repository.local.Db

val MainFeatureModule = module {
    viewModel {
        DashboardFeatureScreenViewModel(
            sessionName = get(named("SESSION_NAME")),
            sessionImage = kotlin.runCatching { get<Bitmap>(named("SESSION_IMAGE")) }.getOrNull(),
            divisionsUseCase = get()
        )
    }

    factory<IDivisionsUseCase> {
        MyDivisionsUseCase(get())
    }

    single<DivisionDataSource> { LocalDivisionDataSource(get(named("SESSION_ID")), get<Db>().userDao()) }
}