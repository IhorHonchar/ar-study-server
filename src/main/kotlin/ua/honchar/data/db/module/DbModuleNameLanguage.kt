package ua.honchar.data.db.module

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DbModuleNameLanguage: Table<DbModuleNameLanguageEntity>("module_name_language") {
    val id = int("id").primaryKey().bindTo { it.id }
    val moduleId = int("module_id").bindTo { it.moduleId }
    val languageId = int("language_id").bindTo { it.languageId }
    val translation = varchar("translation").bindTo { it.translation }
}

interface DbModuleNameLanguageEntity: Entity<DbModuleNameLanguageEntity> {
    val id: Int
    val moduleId: Int
    val languageId: Int
    val translation: String

    companion object: Entity.Factory<DbModuleNameLanguageEntity>()
}