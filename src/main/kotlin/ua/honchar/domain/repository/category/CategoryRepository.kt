package ua.honchar.domain.repository.category

import ua.honchar.domain.repository.Result
import ua.honchar.domain.model.CategoryDTO

interface CategoryRepository {

    fun getCategoriesByLang(langId: Int?): Result<List<CategoryDTO>>

    fun getCategories(): Result<List<CategoryDTO>>
}