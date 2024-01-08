package ua.honchar.feature.main.languages

import io.ktor.server.application.*
import ua.honchar.domain.repository.language.LanguageRepository
import ua.honchar.feature.BaseController

class LanguagesController(
    private val repository: LanguageRepository,
    call: ApplicationCall,
): BaseController(call) {

    suspend fun getLanguages() {
        val res = repository.getLanguages()
        call.respondResult(res)
    }
}