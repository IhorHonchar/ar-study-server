package ua.honchar.feature

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import ua.honchar.domain.repository.Result

abstract class BaseController(protected val call: ApplicationCall) {

    protected suspend inline fun <reified T : Any> ApplicationCall.respondResult(result: Result<T>) {
        when (result) {
            is Result.Success -> {
                call.respond(
                    HttpStatusCode.OK,
                    result.data
                )
            }

            is Result.Error -> {
                call.respond(
                    HttpStatusCode.BadRequest,
                    result.message
                )
            }
        }
    }

}