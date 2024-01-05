package ua.honchar.feature.main.modules

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ua.honchar.domain.repository.module.ModuleRepository

fun Routing.modules() {

    val repository by inject<ModuleRepository>()

    post("modules") {
        val controller = ModulesController(repository, call)
        controller.getModuleByCategory()
    }
}