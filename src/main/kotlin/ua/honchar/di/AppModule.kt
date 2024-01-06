package ua.honchar.di

import org.koin.dsl.module
import org.ktorm.database.Database
import ua.honchar.data.db.DatabaseFactory
import ua.honchar.data.db.DatabaseManager
import ua.honchar.data.repository.category.CategoryRepositoryImpl
import ua.honchar.data.repository.lesson.LessonRepositoryImpl
import ua.honchar.data.repository.model.ModelRepositoryImpl
import ua.honchar.data.repository.module.ModuleRepositoryImpl
import ua.honchar.domain.repository.category.CategoryRepository
import ua.honchar.domain.repository.lesson.LessonRepository
import ua.honchar.domain.repository.model.ModelRepository
import ua.honchar.domain.repository.module.ModuleRepository

val appModule = module {
    single<Database> { DatabaseFactory.create() }
    single { DatabaseManager(get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
    single<ModelRepository> { ModelRepositoryImpl(get()) }
    single<ModuleRepository> { ModuleRepositoryImpl(get()) }
    single<LessonRepository> { LessonRepositoryImpl(get()) }
}