package ua.honchar.domain.repository

import ua.honchar.data.repository.Result
import ua.honchar.domain.model.Category

interface Repository {

    fun getCategoriesByLang(langId: Int?): Result<List<Category>>

    fun getCategories(): Result<List<Category>>
}