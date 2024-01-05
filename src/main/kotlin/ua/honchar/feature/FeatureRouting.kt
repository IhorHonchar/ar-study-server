package ua.honchar.feature

import io.ktor.server.routing.*
import ua.honchar.feature.main.categories.categories
import ua.honchar.feature.main.modules.modules
import ua.honchar.feature.search.models.models

fun Routing.features() {
    categories()
    models()
    modules()
}