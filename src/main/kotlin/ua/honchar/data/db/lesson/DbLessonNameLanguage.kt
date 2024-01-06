package ua.honchar.data.db.lesson

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar
import ua.honchar.data.db.category.DbCategoryNameLanguage.bindTo

object DbLessonNameLanguage: Table<DbLessonNameLanguageEntity>("lesson_name_language") {
    val id = int("id").primaryKey().bindTo { it.id }
    val lessonId = int("lesson_id").bindTo { it.lessonId }
    val languageId = int("language_id").bindTo { it.languageId }
    val translation = varchar("translation").bindTo { it.translation }
}

interface DbLessonNameLanguageEntity: Entity<DbLessonNameLanguageEntity> {
    val id: Int
    val lessonId: Int
    val languageId: Int
    val translation: String

    companion object: Entity.Factory<DbLessonNameLanguageEntity>()
}