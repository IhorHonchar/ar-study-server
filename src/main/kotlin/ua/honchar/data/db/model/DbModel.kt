package ua.honchar.data.db.model

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DbModel: Table<DbModelEntity>("models") {
    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
}

interface DbModelEntity: Entity<DbModelEntity> {
    val id: Int
    val name: String

    companion object: Entity.Factory<DbModelEntity>()
}