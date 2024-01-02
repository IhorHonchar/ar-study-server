package ua.honchar.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import ua.honchar.plugins.routing.categories

fun Application.configureRouting() {
    routing {
        categories()
        staticResources("/", "static")
    }
}
