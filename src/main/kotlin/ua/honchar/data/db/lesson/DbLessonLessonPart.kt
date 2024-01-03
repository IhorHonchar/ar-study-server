package ua.honchar.data.db.lesson

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object DbLessonLessonPart: Table<DbLessonLessonPartEntity>("lesson_lesson_part") {
    val id = int("id").primaryKey().bindTo { it.id }
    val lessonId = int("lesson_id").bindTo { it.lessonId }
    val lessonPartId =  int("lesson_part_id").bindTo { it.lessonPartId }
}

interface DbLessonLessonPartEntity: Entity<DbLessonLessonPartEntity> {
    val id: Int
    val lessonId: Int
    val lessonPartId: Int

    companion object: Entity.Factory<DbLessonLessonPartEntity>()
}