package ua.honchar.data.db.category

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DbCategory: Table<DbCategoryEntity>("categories") {
    val id = int("id").primaryKey().bindTo { it.id }
    val imageName = varchar("image_name").bindTo { it.imageName }
    val order = int("order").bindTo { it.order }
}

interface DbCategoryEntity: Entity<DbCategoryEntity> {
    val id: Int
    val imageName: String
    val order: Int

    companion object: Entity.Factory<DbCategoryEntity>()
}