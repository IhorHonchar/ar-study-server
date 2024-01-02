package ua.honchar.data.db

import org.ktorm.database.Database
import org.ktorm.dsl.*
import ua.honchar.data.db.category.DbCategory
import ua.honchar.data.db.category.DbCategoryLanguage
import ua.honchar.data.db.languages.DBLanguage
import ua.honchar.domain.model.Category

class DatabaseManager(private val database: Database) {

    fun getAllCategoriesByLang(langId: Int): List<Category> {
        return database.from(DbCategory)
            .innerJoin(DbCategoryLanguage, on = DbCategory.id eq DbCategoryLanguage.categoryId)
            .select(DbCategory.id, DbCategoryLanguage.translation, DbCategory.imageName)
            .where(DbCategoryLanguage.languageId eq langId)
            .map { query ->
                val id = query[DbCategory.id]
                val name = query[DbCategoryLanguage.translation]
                val imageName = query[DbCategory.imageName]
                val order = query[DbCategory.order]
                if (id != null && name != null && imageName != null) {
                    Category(
                        id = id,
                        name = name,
                        order = order ?: 0
                    ).apply {
                        setCategoryImagePath(imageName)
                    }
                } else null
            }
            .mapNotNull { it }
    }

    fun getDefaultLangId(): Int? {
        return database.from(DBLanguage)
            .select(DBLanguage.id)
            .orderBy(DBLanguage.id.asc())
            .map { row ->
                row[DBLanguage.id]
            }
            .firstNotNullOfOrNull { it }
    }
}