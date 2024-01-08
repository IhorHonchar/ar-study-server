package ua.honchar.data.repository.language

import ua.honchar.data.db.DatabaseManager
import ua.honchar.domain.model.LanguageDTO
import ua.honchar.domain.repository.Result
import ua.honchar.domain.repository.language.LanguageRepository

class LanguageRepositoryImpl(private val databaseManager: DatabaseManager) : LanguageRepository {

    override fun getLanguages(): Result<List<LanguageDTO>> = Result.Success(databaseManager.getAllLanguages())
}