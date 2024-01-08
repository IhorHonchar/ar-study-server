package ua.honchar.data.db

import org.ktorm.database.Database
import org.ktorm.dsl.*
import ua.honchar.data.db.category.DbCategory
import ua.honchar.data.db.category.DbCategoryLanguage
import ua.honchar.data.db.category.DbCategoryModule
import ua.honchar.data.db.category.DbCategoryNameLanguage
import ua.honchar.data.db.language.DbLanguage
import ua.honchar.data.db.lesson.DbLesson
import ua.honchar.data.db.lesson.DbLessonLessonPart
import ua.honchar.data.db.lesson.DbLessonNameLanguage
import ua.honchar.data.db.lesson_part.DbLessonPart
import ua.honchar.data.db.lesson_part.DbLessonPartTextLanguage
import ua.honchar.data.db.model.DbModel
import ua.honchar.data.db.model.DbModelNameLanguage
import ua.honchar.data.db.module.DbModule
import ua.honchar.data.db.module.DbModuleInfoLanguage
import ua.honchar.data.db.module.DbModuleLesson
import ua.honchar.data.db.module.DbModuleNameLanguage
import ua.honchar.data.db.user.DbUser
import ua.honchar.domain.model.*

class DatabaseManager(private val database: Database) {

    fun getAllCategoriesByLang(langId: Int): List<CategoryDTO> {
        return database.from(DbCategory)
            .innerJoin(DbCategoryLanguage, on = DbCategory.id eq DbCategoryLanguage.categoryId)
            .select(DbCategory.id, DbCategoryLanguage.translation, DbCategory.imageName, DbCategory.order)
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
        return database.from(DbLanguage)
            .select(DbLanguage.id)
            .orderBy(DbLanguage.id.asc())
            .map { row ->
                row[DbLanguage.id]
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
            .leftJoin(DbLessonPart, on = DbModel.id eq DbLessonPart.modelId)
            .leftJoin(DbLessonLessonPart, on = DbLessonPart.id eq DbLessonLessonPart.lessonPartId)
            .leftJoin(DbModuleLesson, on = DbLessonLessonPart.lessonId eq DbModuleLesson.lessonId)
            .leftJoin(DbCategoryModule, on = DbModuleLesson.moduleId eq DbCategoryModule.moduleId)
            .leftJoin(DbCategory, on = DbCategoryModule.categoryId eq DbCategory.id)
            .leftJoin(
                DbModelNameLanguage,
                on = (DbModel.id eq DbModelNameLanguage.modelId) and (DbModelNameLanguage.languageId eq langId)
            )
            .leftJoin(
                DbCategoryNameLanguage,
                on = (DbCategory.id eq DbCategoryNameLanguage.categoryId) and (DbCategoryNameLanguage.languageId eq langId)
            )
            .selectDistinct(
                DbModel.id,
                DbModel.fileName,
                DbModelNameLanguage.name,
                DbCategory.id,
                DbCategoryNameLanguage.translation
            )
            .where { (DbModelNameLanguage.languageId eq langId) or (DbModelNameLanguage.languageId.isNull()) }
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

    fun getLessonsByModuleId(moduleId: Int, langId: Int): List<LessonDTO> {
        val lessonsByModule = database.from(DbLesson)
            .innerJoin(DbLessonNameLanguage, on = DbLesson.id eq DbLessonNameLanguage.lessonId)
            .innerJoin(DbModuleLesson, on = DbLesson.id eq DbModuleLesson.lessonId)
            .select(DbLesson.id, DbLessonNameLanguage.translation)
            .where { (DbModuleLesson.moduleId eq moduleId) and (DbLessonNameLanguage.languageId eq langId) }
            .map {
                val lessonId = it[DbLesson.id]
                val lessonName = it[DbLessonNameLanguage.translation]
                if (lessonId != null && lessonName != null) {
                    LessonDTO(
                        id = lessonId,
                        name = lessonName,
                        emptyList()
                    )
                } else {
                    null
                }
            }
            .mapNotNull { it }
        val lessonPart = database.from(DbLessonPart)
            .innerJoin(DbLessonLessonPart, on = DbLessonPart.id eq DbLessonLessonPart.lessonPartId)
            .leftJoin(DbLessonPartTextLanguage, on = DbLessonPart.id eq DbLessonPartTextLanguage.lessonPartId)
            .leftJoin(DbModel, on = DbLessonPart.modelId eq DbModel.id)
            .leftJoin(DbModelNameLanguage, on = DbModel.id eq DbModelNameLanguage.modelId)
            .select(
                DbLessonLessonPart.lessonId,
                DbLessonPart.id,
                DbLessonPartTextLanguage.translation,
                DbModel.id,
                DbModel.fileName,
                DbModelNameLanguage.name
            )
            .where { (DbLessonLessonPart.lessonId inList lessonsByModule.map { it.id }) and (DbLessonPartTextLanguage.languageId eq langId) and (DbModelNameLanguage.languageId eq langId) }
            .map {
                val lessonPartId = it[DbLessonPart.id]
                val lessonPartText = it[DbLessonPartTextLanguage.translation]
                val lessonId = it[DbLessonLessonPart.lessonId]
                if (lessonPartId != null && lessonPartText != null && lessonId != null) {
                    lessonId to LessonPartDTO(
                        id = lessonPartId,
                        text = lessonPartText,
                        model = ModelDTO(
                            id = it[DbModel.id],
                            fileName = it[DbModel.fileName],
                            name = it[DbModelNameLanguage.name],
                            categoryId = null,
                            categoryName = null
                        )
                    )
                } else {
                    null
                }
            }.mapNotNull { it }
        return lessonsByModule.map { lessonModel ->
            val lessonPartsByLesson = lessonPart.filter { (lessonId, _) ->
                lessonId == lessonModel.id
            }.map { it.second }
            lessonModel.copy(lessonParts = lessonPartsByLesson)
        }
    }

    fun getAllLanguages(): List<LanguageDTO> {
        return database.from(DbLanguage)
            .select(DbLanguage.id, DbLanguage.language)
            .map {
                val id = it[DbLanguage.id]
                val language = it[DbLanguage.language]
                if (id != null && language != null) {
                    LanguageDTO(id, language)
                } else {
                    null
                }
            }
            .mapNotNull { it }
    }

    fun getUserData(login: String, pass: String): UserDTO? {
        return database.from(DbUser)
            .select(DbUser.login, DbUser.name)
            .where { (DbUser.login eq login) and (DbUser.pass eq pass) }
            .map {
                val loginId = it[DbUser.login]
                if (loginId != null) {
                    UserDTO(
                        name = it[DbUser.name].orEmpty(),
                        login = loginId,
                    )
                } else {
                    null
                }
            }
            .firstNotNullOfOrNull { it }
    }

    fun insertUser(login: String, pass: String, name: String): UserDTO? {
        val userExists = database.from(DbUser).select().where { DbUser.login eq login }.totalRecordsInAllPages > 0

        return if (!userExists) {
            database.insert(DbUser) {
                set(it.login, login)
                set(it.name, name)
                set(it.pass, pass)
            }
            UserDTO(name, login)
        } else {
            null
        }
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