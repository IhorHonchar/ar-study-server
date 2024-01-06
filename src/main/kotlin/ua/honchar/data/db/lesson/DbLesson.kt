package ua.honchar.data.db.lesson

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object DbLesson: Table<DbLessonEntity>("lesson") {
    val id = int("id").primaryKey().bindTo { it.id }
}

interface DbLessonEntity: Entity<DbLessonEntity> {
    val id: Int

    companion object: Entity.Factory<DbLessonEntity>()
}