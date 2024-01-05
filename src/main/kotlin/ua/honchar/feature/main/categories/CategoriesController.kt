package ua.honchar.feature.main.categories

import io.ktor.server.application.*
import ua.honchar.domain.repository.category.CategoryRepository
import ua.honchar.feature.BaseController

class CategoriesController(
    private val categoryRepository: CategoryRepository,
    call: ApplicationCall
): BaseController(call) {

    suspend fun getCategories(langIdParam: String) {
        val langId = call.parameters[langIdParam]?.toIntOrNull()
        val res = categoryRepository.getCategoriesByLang(langId)
        call.respondResult(res)
    }

    suspend fun getCategories() {
        val res = categoryRepository.getCategories()
        call.respondResult(res)
    }
}