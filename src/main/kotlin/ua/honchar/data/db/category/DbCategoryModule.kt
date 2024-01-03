package ua.honchar.data.db.category

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object DbCategoryModule: Table<DbCategoryModuleEntity>("categories_module") {
    val id = int("id").primaryKey().bindTo { it.id }
    val categoryId = int("category_id").bindTo { it.categoryId }
    val moduleId = int("module_id").bindTo { it.moduleId }
}

interface DbCategoryModuleEntity: Entity<DbCategoryModuleEntity> {
    val id: Int
    val categoryId: Int
    val moduleId: Int

    companion object: Entity.Factory<DbCategoryModuleEntity>()
}