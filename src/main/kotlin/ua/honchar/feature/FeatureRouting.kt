package ua.honchar.feature

import io.ktor.server.routing.*
import ua.honchar.feature.main.categories.categories
import ua.honchar.feature.main.languages.languages
import ua.honchar.feature.main.lessons.lessons
import ua.honchar.feature.main.modules.modules
import ua.honchar.feature.main.users.users
import ua.honchar.feature.search.models.models

fun Routing.features() {
    categories()
    models()
    modules()
    lessons()
    languages()
    users()
}