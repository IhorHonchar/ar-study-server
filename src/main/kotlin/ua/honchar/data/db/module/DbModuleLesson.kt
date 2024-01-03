package ua.honchar.data.db.module

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object DbModuleLesson: Table<DbModuleLessonEntity>("modules_lessons") {
    val id = int("id").primaryKey().bindTo { it.id }
    val moduleId = int("module_id").bindTo { it.moduleId }
    val lessonId = int("lesson_id").bindTo { it.lessonId }
}

interface DbModuleLessonEntity: Entity<DbModuleLessonEntity> {
    val id: Int
    val moduleId: Int
    val lessonId: Int

    companion object: Entity.Factory<DbModuleLessonEntity>()
}