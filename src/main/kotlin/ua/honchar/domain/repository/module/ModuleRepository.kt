package ua.honchar.domain.repository.module

import ua.honchar.domain.repository.Result
import ua.honchar.domain.model.ModuleDTO

interface ModuleRepository {

    suspend fun getModuleByCategory(categoryId: Int, langId: Int?): Result<List<ModuleDTO>>
}