package ua.honchar.data.repository.module

import ua.honchar.data.db.DatabaseManager
import ua.honchar.domain.repository.Result
import ua.honchar.domain.model.ModuleDTO
import ua.honchar.domain.repository.module.ModuleRepository

class ModuleRepositoryImpl(
    private val database: DatabaseManager
) : ModuleRepository {

    override suspend fun getModuleByCategory(categoryId: Int, langId: Int?): Result<List<ModuleDTO>> {
        return if (langId != null) {
            Result.Success(database.getModulesByCategory(categoryId, langId))
        } else {
            database.getDefaultLangId()?.let { defaultLangId ->
                Result.Success(database.getModulesByCategory(categoryId, defaultLangId))
            } ?: Result.Error("there is no any languages")
        }
    }
}