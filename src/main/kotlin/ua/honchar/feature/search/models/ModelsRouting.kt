package ua.honchar.feature.search.models

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ua.honchar.domain.repository.model.ModelRepository

fun Routing.models() {
    val repository by inject<ModelRepository>()

    post("models") {
        val controller = ModelController(repository, call)
        controller.getModels()
    }
}
