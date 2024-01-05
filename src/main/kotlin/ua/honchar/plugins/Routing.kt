package ua.honchar.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import ua.honchar.feature.features
import ua.honchar.feature.main.categories.categories
import ua.honchar.feature.search.models.models

fun Application.configureRouting() {
    routing {
        features()
        staticResources("/", "static")
    }
}
