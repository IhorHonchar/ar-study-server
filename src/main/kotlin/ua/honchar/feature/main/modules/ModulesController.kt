package ua.honchar.feature.main.modules

import io.ktor.server.application.*
import io.ktor.server.request.*
import ua.honchar.domain.repository.Result
import ua.honchar.domain.repository.module.ModuleRepository
import ua.honchar.feature.BaseController

class ModulesController(
    private val repository: ModuleRepository,
    call: ApplicationCall
) : BaseController(call) {

    suspend fun getModuleByCategory() {
        val moduleData = call.receive<ReceiveModuleData>()
        val res = when (moduleData.categoryId) {
            null -> {
                Result.Error("categoryId is missing")
            }

            else -> {
                repository.getModuleByCategory(moduleData.categoryId, moduleData.langId)
            }
        }
        call.respondResult(res)
    }
}