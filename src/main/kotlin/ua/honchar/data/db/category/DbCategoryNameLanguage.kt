package ua.honchar.data.db.category

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DbCategoryNameLanguage: Table<DbCategoryNameLanguageEntity>("category_name_language") {
    val id = int("id").primaryKey().bindTo { it.id }
    val languageId = int("language_id").bindTo { it.languageId }
    val categoryId = int("category_id").bindTo { it.categoryId }
    val translation = varchar("translation").bindTo { it.translation }
}

interface DbCategoryNameLanguageEntity: Entity<DbCategoryNameLanguageEntity> {
    val id: Int
    val languageId: Int
    val categoryId: Int
    val translation: String

    companion object: Entity.Factory<DbCategoryNameLanguageEntity>()
}