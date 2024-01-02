package ua.honchar.data.db.languages

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DBLanguage: Table<DbLanguageEntity>("languages") {
    val id = int("id").primaryKey().bindTo { it.id }
    val language = varchar("language").bindTo { it.language }
}

interface DbLanguageEntity: Entity<DbLanguageEntity> {
    val id: Int
    val language: String

    companion object : Entity.Factory<DbLanguageEntity>()
}