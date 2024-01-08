package ua.honchar.domain.repository.language

import ua.honchar.domain.model.LanguageDTO
import ua.honchar.domain.repository.Result

interface LanguageRepository {

    fun getLanguages(): Result<List<LanguageDTO>>
}