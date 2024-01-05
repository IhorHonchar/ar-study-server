package ua.honchar.data.db

import org.ktorm.database.Database
import org.ktorm.dsl.*
import ua.honchar.data.db.category.DbCategory
import ua.honchar.data.db.category.DbCategoryLanguage
import ua.honchar.data.db.category.DbCategoryModule
import ua.honchar.data.db.category.DbCategoryNameLanguage
import ua.honchar.data.db.languages.DBLanguage
import ua.honchar.data.db.lesson.DbLessonLessonPart
import ua.honchar.data.db.lesson_part.DbLessonPart
import ua.honchar.data.db.model.DbModel
import ua.honchar.data.db.model.DbModelNameLanguage
import ua.honchar.data.db.module.DbModule
import ua.honchar.data.db.module.DbModuleInfoLanguage
import ua.honchar.data.db.module.DbModuleLesson
import ua.honchar.data.db.module.DbModuleNameLanguage
import ua.honchar.domain.model.CategoryDTO
import ua.honchar.domain.model.ModelDTO
import ua.honchar.domain.model.ModuleDTO

class DatabaseManager(private val database: Database) {

    fun getAllCategoriesByLang(langId: Int): List<CategoryDTO> {
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
                    CategoryDTO(
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

    fun getModelsByCategory(categoryId: Int, langId: Int): List<ModelDTO> {
        return database.from(DbCategoryModule)
            .innerJoin(DbModuleLesson, on = DbCategoryModule.moduleId eq DbModuleLesson.moduleId)
            .innerJoin(DbLessonLessonPart, on = DbModuleLesson.lessonId eq DbLessonLessonPart.lessonId)
            .innerJoin(DbLessonPart, on = DbLessonLessonPart.lessonPartId eq DbLessonPart.id)
            .innerJoin(DbModel, on = DbLessonPart.modelId eq DbModel.id)
            .innerJoin(DbModelNameLanguage, on = DbModel.id eq DbModelNameLanguage.modelId)
            .select(DbModel.id, DbModel.fileName, DbModelNameLanguage.name)
            .where { (DbCategoryModule.categoryId eq categoryId) and (DbModelNameLanguage.languageId eq langId) }
            .map(::queryToModel)
            .mapNotNull { it }
    }

    fun getAllModels(langId: Int): List<ModelDTO> {
        return database.from(DbModel)
            .innerJoin(DbLessonPart, on = DbModel.id eq DbLessonPart.modelId)
            .innerJoin(DbLessonLessonPart, on = DbLessonPart.id eq DbLessonLessonPart.lessonPartId)
            .innerJoin(DbModuleLesson, on = DbLessonLessonPart.lessonId eq DbModuleLesson.lessonId)
            .innerJoin(DbCategoryModule, on = DbModuleLesson.moduleId eq DbCategoryModule.moduleId)
            .innerJoin(DbCategory, on = DbCategoryModule.categoryId eq DbCategory.id)
            .innerJoin(DbModelNameLanguage, on = DbModel.id eq DbModelNameLanguage.modelId)
            .innerJoin(DbCategoryNameLanguage, on = DbCategory.id eq DbCategoryNameLanguage.categoryId)
            .select(DbModel.id, DbModel.fileName, DbModelNameLanguage.name, DbCategory.id, DbCategoryNameLanguage.translation)
            .where {(DbModelNameLanguage.languageId eq langId) and (DbCategoryNameLanguage.languageId eq langId)}
            .map(::queryToModel)
            .mapNotNull { it }
    }

    fun getModulesByCategory(categoryId: Int, langId: Int): List<ModuleDTO> {
        return database.from(DbCategoryModule)
            .innerJoin(DbModule, on = DbCategoryModule.moduleId eq DbModule.id)
            .innerJoin(DbModuleNameLanguage, on = DbModule.id eq DbModuleNameLanguage.moduleId)
            .innerJoin(DbModuleInfoLanguage, on = DbModule.id eq DbModuleInfoLanguage.moduleId)
            .select(DbModule.id, DbModuleNameLanguage.translation, DbModuleInfoLanguage.translation)
            .where {
                (DbCategoryModule.categoryId eq categoryId) and
                        (DbModuleNameLanguage.languageId eq langId) and
                        (DbModuleInfoLanguage.languageId eq langId)
            }
            .map {
                val id = it[DbModule.id]
                val name = it[DbModuleNameLanguage.translation]
                val info = it[DbModuleInfoLanguage.translation]
                if (id != null && name != null && info != null) {
                    ModuleDTO(id, name, info)
                } else {
                    null
                }
            }
            .mapNotNull { it }
    }

    private fun queryToModel(query: QueryRowSet): ModelDTO? {
        val id = query[DbModel.id]
        val fileName = query[DbModel.fileName]
        val modelName = query[DbModelNameLanguage.name]
        val categoryId = query[DbCategory.id]
        val categoryName = query[DbCategoryNameLanguage.translation]
        return if (id != null && fileName != null && modelName != null) {
            ModelDTO(id, modelName, fileName, categoryId, categoryName)
        } else {
            null
        }
    }
}