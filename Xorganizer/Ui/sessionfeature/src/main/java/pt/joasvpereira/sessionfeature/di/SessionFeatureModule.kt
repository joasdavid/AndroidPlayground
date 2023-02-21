package pt.joasvpereira.xorganizer.di

import com.joasvpereira.sessioncore.domail.usecases.ILoadSessionUseCase
import com.joasvpereira.sessioncore.domail.usecases.ISessionsUseCase
import com.joasvpereira.sessioncore.domail.usecases.LoadSessionUseCase
import com.joasvpereira.sessioncore.domail.usecases.SessionsUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pt.joasvpereira.sessionfeature.domain.usecase.CreateSessionUseCase
import pt.joasvpereira.sessionfeature.domain.usecase.DeleteSessionUseCase
import pt.joasvpereira.sessionfeature.domain.usecase.ICreateSessionUseCase
import pt.joasvpereira.sessionfeature.domain.usecase.IDeleteSessionUseCase
import pt.joasvpereira.sessionfeature.domain.usecase.ISessionUseCase
import pt.joasvpereira.sessionfeature.domain.usecase.SessionUseCase
import pt.joasvpereira.sessionfeature.presentation.create.CreateSessionFeatureScreenViewModel
import pt.joasvpereira.sessionfeature.presentation.select.session.SelectSessionViewModel

val sessionFeatureModule = module {
    viewModel {
        SelectSessionViewModel(get())
    }

    viewModel {
        CreateSessionFeatureScreenViewModel(
            sessionUseCase = get(),
            createSessionUseCase = get(),
            deleteSessionUseCase = get()
        )
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

    single<IDeleteSessionUseCase> {
        DeleteSessionUseCase(get())
    }

    single<ILoadSessionUseCase> {
        LoadSessionUseCase(get())
    }
}
