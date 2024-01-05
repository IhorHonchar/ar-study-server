package ua.honchar.feature.main.categories

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ua.honchar.domain.repository.category.CategoryRepository

fun Routing.categories() {
    val langIdParam = "langId"
    val repository by inject<CategoryRepository>()

    route("categories") {

        get {
            val controller = CategoriesController(repository, call)
            controller.getCategories()
        }

        get("{$langIdParam}") {
            val controller = CategoriesController(repository, call)
            controller.getCategories(langIdParam)
        }
    }
}