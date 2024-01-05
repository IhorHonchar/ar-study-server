package ua.honchar.domain.model

import kotlinx.serialization.Serializable
import ua.honchar.core.getModelPath

@Serializable
class ModelDTO {
    constructor(id: Int, name: String, fileName: String, categoryId: Int?, categoryName: String?) {
        this.name = name
        this.id = id
        this.fileName = fileName
        this.categoryId = categoryId
        this.categoryName = categoryName
        modelPath = getModelPath("models/$fileName")
    }

    val name: String
    val id: Int
    val fileName: String
    val categoryId: Int?
    val categoryName: String?
    private var modelPath: String = ""
}
