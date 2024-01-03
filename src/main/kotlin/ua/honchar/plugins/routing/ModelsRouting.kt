package ua.honchar.plugins.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ua.honchar.data.repository.Result
import ua.honchar.domain.repository.Repository

fun Routing.models() {
    val repository by inject<Repository>()

    get("models/{categoryId}") {
        val categoryId = call.parameters["categoryId"]?.toIntOrNull() ?: 0
        when (val res = repository.getModelsByCategory(categoryId)) {
            is Result.Success -> {
                call.respond(
                    HttpStatusCode.OK,
                    res.data
                )
            }

            is Result.Error -> {
                call.respond(
                    HttpStatusCode.NoContent,
                    res.message
                )
            }
        }
    }
}