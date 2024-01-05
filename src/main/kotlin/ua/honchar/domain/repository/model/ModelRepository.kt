package ua.honchar.domain.repository.model

import ua.honchar.domain.repository.Result
import ua.honchar.domain.model.ModelDTO

interface ModelRepository {
    fun getModelsByCategory(categoryId: Int, langId: Int?): Result<List<ModelDTO>>

    fun getAllModels(langId: Int?): Result<List<ModelDTO>>
}