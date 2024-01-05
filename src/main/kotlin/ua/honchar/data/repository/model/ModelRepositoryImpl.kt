package ua.honchar.data.repository.model

import ua.honchar.data.db.DatabaseManager
import ua.honchar.domain.repository.Result
import ua.honchar.domain.model.ModelDTO
import ua.honchar.domain.repository.model.ModelRepository

class ModelRepositoryImpl(
    private val database: DatabaseManager
): ModelRepository {

    override fun getModelsByCategory(categoryId: Int, langId: Int?): Result<List<ModelDTO>> {
        return if (langId != null) {
            Result.Success(database.getModelsByCategory(categoryId, langId))
        } else {
            database.getDefaultLangId()?.let { defaultLangId ->
                Result.Success(database.getModelsByCategory(categoryId, defaultLangId))
            } ?: Result.Error("there is no any languages")
        }
    }

    override fun getAllModels(langId: Int?): Result<List<ModelDTO>> {
        return if (langId != null) {
            Result.Success(database.getAllModels(langId))
        } else {
            database.getDefaultLangId()?.let { defaultLangId ->
                Result.Success(database.getAllModels(defaultLangId))
            } ?: Result.Error("there is no any languages")
        }
    }
}