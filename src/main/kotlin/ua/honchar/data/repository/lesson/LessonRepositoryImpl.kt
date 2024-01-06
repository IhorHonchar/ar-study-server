package ua.honchar.data.repository.lesson

import ua.honchar.data.db.DatabaseManager
import ua.honchar.domain.model.LessonDTO
import ua.honchar.domain.repository.Result
import ua.honchar.domain.repository.lesson.LessonRepository

class LessonRepositoryImpl(private val databaseManager: DatabaseManager) : LessonRepository {
    override fun getLessonsByModuleId(moduleId: Int, langId: Int?): Result<List<LessonDTO>> {
        return if (langId != null) {
            Result.Success(databaseManager.getLessonsByModuleId(moduleId, langId))
        } else {
            databaseManager.getDefaultLangId()?.let { defaultLangId ->
                Result.Success(databaseManager.getLessonsByModuleId(moduleId, defaultLangId))
            } ?: Result.Error("there is no any languages")
        }
    }
}