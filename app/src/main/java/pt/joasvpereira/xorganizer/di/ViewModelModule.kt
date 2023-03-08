package pt.joasvpereira.xorganizer.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import pt.joasvpereira.core.repository.CurrentSession
import pt.joasvpereira.xorganizer.presentation.MainViewModel

val appModule = module {

    viewModel {
        MainViewModel(get())
    }

    factory<Int?>(named("SESSION_ID")) {
        CurrentSession.sessionId
    }
}
