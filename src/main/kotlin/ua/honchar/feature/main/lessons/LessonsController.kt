package ua.honchar.feature.main.lessons

import io.ktor.server.application.*
import io.ktor.server.request.*
import ua.honchar.domain.repository.Result
import ua.honchar.domain.repository.lesson.LessonRepository
import ua.honchar.feature.BaseController

class LessonsController(
    private val repository: LessonRepository,
    call: ApplicationCall,
): BaseController(call) {

    suspend fun getLessonsByModule() {
        val lessonData = call.receive<ReceiveLessonData>()
        val res = when (lessonData.moduleId) {
            null -> {
                Result.Error("moduleId is missing")
            }

            else -> {
                repository.getLessonsByModuleId(lessonData.moduleId, lessonData.langId)
            }
        }
        call.respondResult(res)
    }
}