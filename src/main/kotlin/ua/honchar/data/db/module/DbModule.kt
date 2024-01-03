package ua.honchar.data.db.module

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object DbModule: Table<DbModuleEntity>("module") {
    val id = int("id").primaryKey().bindTo { it.id }
}

interface DbModuleEntity: Entity<DbModuleEntity> {
    val id: Int

    companion object: Entity.Factory<DbModuleEntity>()
}