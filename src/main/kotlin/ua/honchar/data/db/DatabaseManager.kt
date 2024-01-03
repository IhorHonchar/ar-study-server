package ua.honchar.data.db

import org.ktorm.database.Database
import org.ktorm.dsl.*
import ua.honchar.data.db.category.DbCategory
import ua.honchar.data.db.category.DbCategoryLanguage
import ua.honchar.data.db.category.DbCategoryModule
import ua.honchar.data.db.languages.DBLanguage
import ua.honchar.data.db.lesson.DbLessonLessonPart
import ua.honchar.data.db.lesson_part.DbLessonPart
import ua.honchar.data.db.model.DbModel
import ua.honchar.data.db.module.DbModule
import ua.honchar.data.db.module.DbModuleLesson
import ua.honchar.domain.model.Category
import ua.honchar.domain.model.Model

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

    fun getModelsByCategory(categoryId: Int): List<Model> {
        return database.from(DbCategoryModule)
            .innerJoin(DbModule, on = DbCategoryModule.moduleId eq DbModule.id)
            .innerJoin(DbModuleLesson, on = DbModule.id eq DbModuleLesson.moduleId)
            .innerJoin(DbLessonLessonPart, on = DbModuleLesson.lessonId eq DbLessonLessonPart.lessonId)
            .innerJoin(DbLessonPart, on = DbLessonLessonPart.lessonPartId eq DbLessonPart.id)
            .innerJoin(DbModel, on = DbLessonPart.modelId eq DbModel.id)
            .select(DbModel.id, DbModel.name)
            .where(DbCategoryModule.categoryId eq categoryId)
            .map { query ->
                val id = query[DbModel.id]
                val name = query[DbModel.name]
                if (id != null && name != null) {
                    Model(id, name)
                } else {
                    null
                }
            }
            .mapNotNull { it }
    }
}