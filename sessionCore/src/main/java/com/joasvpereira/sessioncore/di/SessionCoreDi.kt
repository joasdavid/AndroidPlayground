package com.joasvpereira.sessioncore.di

import com.joasvpereira.sessioncore.domail.usecases.ILoadSessionUseCase
import com.joasvpereira.sessioncore.domail.usecases.LoadSessionUseCase
import com.joasvpereira.sessioncore.repository.SessionPreferencesDataSource
import com.joasvpereira.sessioncore.repository.local.LocalSessionPreferencesDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pt.joasvpereira.core.repository.local.Db
import pt.joasvpereira.sessionfeature.repository.LocalSessionDataSource
import pt.joasvpereira.sessionfeature.repository.SessionDataSource

val sessionCoreModule = module {
    single<SessionDataSource> { LocalSessionDataSource(get<Db>().sessionDao()) }

    single<SessionPreferencesDataSource> {
        LocalSessionPreferencesDataSource(androidContext())
    }

    single<ILoadSessionUseCase> {
        LoadSessionUseCase(
            sessionsUseCase = get(),
            sessionPreferencesDataSource = get(),
        )
    }
}
