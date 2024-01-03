package ua.honchar.data.db.lesson_part

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object DbLessonPart: Table<DbLessonPartEntity>("lesson_part") {
    val id = int("id").primaryKey().bindTo { it.id }
    val modelId = int("model_id").bindTo { it.modelId }
}

interface DbLessonPartEntity: Entity<DbLessonPartEntity> {
    val id: Int
    val modelId: Int

    companion object: Entity.Factory<DbLessonPartEntity>()
}