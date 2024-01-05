package ua.honchar.data.repository.category

import ua.honchar.data.db.DatabaseManager
import ua.honchar.domain.repository.Result
import ua.honchar.domain.model.CategoryDTO
import ua.honchar.domain.repository.category.CategoryRepository

class CategoryRepositoryImpl(
    private val database: DatabaseManager
) : CategoryRepository {

    override fun getCategoriesByLang(langId: Int?): Result<List<CategoryDTO>> {
        return if (langId != null) {
            Result.Success(database.getAllCategoriesByLang(langId))
        } else {
            getCategories()
        }
    }

    override fun getCategories(): Result<List<CategoryDTO>> {
        val defaultLangId = database.getDefaultLangId()
        return if (defaultLangId != null) {
            Result.Success(database.getAllCategoriesByLang(defaultLangId))
        } else {
            Result.Error("there is no any languages")
        }
    }
}