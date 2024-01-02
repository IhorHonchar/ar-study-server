package ua.honchar.plugins.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*
import kotlinx.coroutines.delay
import org.koin.ktor.ext.inject
import ua.honchar.data.repository.RepositoryImpl
import ua.honchar.data.repository.Result
import ua.honchar.domain.model.Category
import ua.honchar.domain.repository.Repository

fun Routing.categories() {
    val repository by inject<Repository>()

    route("categories") {

        get {
            val res = repository.getCategories()
            respondResult(res)
        }

        get("{langId}") {
            val langId = call.parameters["langId"]?.toIntOrNull()
            val res = repository.getCategoriesByLang(langId)
            delay(1000L)
            respondResult(res)
        }
    }
}

private suspend fun PipelineContext<*, ApplicationCall>.respondResult(result: Result<List<Category>>) {
    when (result) {
        is Result.Success -> {
            call.respond(
                HttpStatusCode.OK,
                result.data
            )
        }

        is Result.Error -> {
            call.respond(
                HttpStatusCode.NoContent,
                result.message
            )
        }
    }
}