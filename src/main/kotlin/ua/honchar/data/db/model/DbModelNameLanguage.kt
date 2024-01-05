package ua.honchar.data.db.model

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar
import ua.honchar.data.db.module.DbModule.bindTo

object DbModelNameLanguage: Table<DbModelNameLanguageEntity>("model_name_language") {
    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("translation").bindTo { it.name }
    val modelId = int("model_id").bindTo { it.modelId }
    val languageId = int("language_id").bindTo { it.languageId }
}

interface DbModelNameLanguageEntity: Entity<DbModelNameLanguageEntity> {
    val id: Int
    val name: String
    val modelId: Int
    val languageId: Int

    companion object: Entity.Factory<DbModelNameLanguageEntity>()
}