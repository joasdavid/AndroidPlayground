package pt.joasvpereira.xorganizer.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pt.joasvpereira.core.repository.local.Db
import pt.joasvpereira.sessionfeature.domain.usecase.CreateSessionUseCase
import pt.joasvpereira.sessionfeature.domain.usecase.ICreateSessionUseCase
import pt.joasvpereira.sessionfeature.domain.usecase.ISessionUseCase
import pt.joasvpereira.sessionfeature.domain.usecase.ISessionsUseCase
import pt.joasvpereira.sessionfeature.domain.usecase.SessionUseCase
import pt.joasvpereira.sessionfeature.domain.usecase.SessionsUseCase
import pt.joasvpereira.sessionfeature.presentation.create.CreateSessionFeatureScreenViewModel
import pt.joasvpereira.sessionfeature.presentation.select.session.SelectSessionViewModel
import pt.joasvpereira.sessionfeature.repository.LocalSessionDataSource
import pt.joasvpereira.sessionfeature.repository.SessionDataSource

val sessionFeatureModule = module {
    viewModel {
        SelectSessionViewModel(get())
    }

    viewModel {
        CreateSessionFeatureScreenViewModel(get(), get())
    }

    single<ISessionUseCase> {
        SessionUseCase(get())
    }

    single<ISessionsUseCase> {
        SessionsUseCase(get())
    }

    single<ICreateSessionUseCase> {
        CreateSessionUseCase(get())
    }

    single<SessionDataSource> { LocalSessionDataSource(get<Db>().sessionDao()) }
}
