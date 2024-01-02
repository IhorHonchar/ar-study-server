package ua.honchar.di

import org.koin.dsl.module
import org.ktorm.database.Database
import ua.honchar.data.db.DatabaseFactory
import ua.honchar.data.db.DatabaseManager
import ua.honchar.data.repository.RepositoryImpl
import ua.honchar.domain.repository.Repository

val appModule = module {
    single<Database> { DatabaseFactory.create() }
    single { DatabaseManager(get()) }
    single<Repository> { RepositoryImpl(get()) }
}