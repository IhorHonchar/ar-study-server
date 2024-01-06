package ua.honchar.data.db.lesson_part

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DbLessonPartTextLanguage : Table<DbLessonPartTextLanguageEntity>("lesson_part_text_language") {
    val id = int("id").primaryKey().bindTo { it.id }
    val lessonPartId = int("lesson_part_id").bindTo { it.lessonPartId }
    val languageId = int("language_id").bindTo { it.languageId }
    val translation = varchar("translation").bindTo { it.translation }
}

interface DbLessonPartTextLanguageEntity : Entity<DbLessonPartTextLanguageEntity> {
    val id: Int
    val lessonPartId: Int
    val languageId: Int
    val translation: String

    companion object : Entity.Factory<DbLessonPartTextLanguageEntity>()
}