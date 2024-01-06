package ua.honchar.feature.main.lessons

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ua.honchar.domain.repository.lesson.LessonRepository

fun Routing.lessons() {
    val repository by inject<LessonRepository>()

    post("lessons") {
        val controller = LessonsController(repository, call)
        controller.getLessonsByModule()
    }
}