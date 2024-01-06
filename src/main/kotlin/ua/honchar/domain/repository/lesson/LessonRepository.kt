package ua.honchar.domain.repository.lesson

import ua.honchar.domain.model.LessonDTO
import ua.honchar.domain.repository.Result

interface LessonRepository {

    fun getLessonsByModuleId(moduleId: Int, langId: Int?): Result<List<LessonDTO>>
}