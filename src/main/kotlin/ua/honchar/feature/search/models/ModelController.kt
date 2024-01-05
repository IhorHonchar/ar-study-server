package ua.honchar.feature.search.models

import io.ktor.server.application.*
import io.ktor.server.request.*
import ua.honchar.domain.repository.model.ModelRepository
import ua.honchar.feature.BaseController

class ModelController(
    private val modelRepository: ModelRepository,
    call: ApplicationCall
): BaseController(call) {

    suspend fun getModels() {
        val modelData = call.receive<ReceiveModelData>()
        val res = when (modelData.categoryId) {
            null -> {
                modelRepository.getAllModels(modelData.langId)
            }

            else -> {
                modelRepository.getModelsByCategory(modelData.categoryId, modelData.langId)
            }
        }
        call.respondResult(res)
    }
}