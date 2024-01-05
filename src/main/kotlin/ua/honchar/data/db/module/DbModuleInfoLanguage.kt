package ua.honchar.data.db.module

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DbModuleInfoLanguage: Table<DbModuleNameLanguageEntity>("module_info_language") {
    val id = int("id").primaryKey().bindTo { it.id }
    val moduleId = int("module_id").bindTo { it.moduleId }
    val languageId = int("language_id").bindTo { it.languageId }
    val translation = varchar("translation").bindTo { it.translation }
}