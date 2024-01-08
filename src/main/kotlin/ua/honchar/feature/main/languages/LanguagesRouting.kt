package ua.honchar.feature.main.languages

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ua.honchar.domain.repository.language.LanguageRepository

fun Routing.languages() {

    val repository by inject<LanguageRepository>()

    get("languages") {
        val controller = LanguagesController(repository, call)
        controller.getLanguages()
    }
}