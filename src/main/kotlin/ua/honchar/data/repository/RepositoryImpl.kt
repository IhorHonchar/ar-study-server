package ua.honchar.data.repository

import ua.honchar.data.db.DatabaseManager
import ua.honchar.domain.model.Category
import ua.honchar.domain.model.Model
import ua.honchar.domain.repository.Repository

class RepositoryImpl(
    private val database: DatabaseManager
): Repository {

    override fun getCategoriesByLang(langId: Int?): Result<List<Category>> {
        return if (langId != null){
            Result.Success(database.getAllCategoriesByLang(langId))
        } else {
            getCategories()
        }
    }

    override fun getCategories(): Result<List<Category>> {
        val defaultLangId = database.getDefaultLangId()
        return if (defaultLangId != null) {
            Result.Success(database.getAllCategoriesByLang(defaultLangId))
        } else {
            Result.Error("there is no any languages")
        }
    }

    override fun getModelsByCategory(categoryId: Int): Result<List<Model>> {
        return Result.Success(database.getModelsByCategory(categoryId))
    }
}