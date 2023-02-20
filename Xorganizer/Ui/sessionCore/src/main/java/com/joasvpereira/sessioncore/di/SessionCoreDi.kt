package com.joasvpereira.sessioncore.di

import org.koin.dsl.module
import pt.joasvpereira.core.repository.local.Db
import pt.joasvpereira.sessionfeature.repository.LocalSessionDataSource
import pt.joasvpereira.sessionfeature.repository.SessionDataSource

val sessionCoreModule = module {
    single<SessionDataSource> { LocalSessionDataSource(get<Db>().sessionDao()) }
}