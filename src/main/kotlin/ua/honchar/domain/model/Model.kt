package ua.honchar.domain.model

import kotlinx.serialization.Serializable
import ua.honchar.core.getModelPath

@Serializable
class Model {
    constructor(id: Int, name: String) {
        this.name = name
        this.id = id
        modelPath = getModelPath("models/$name")
    }

    val name: String
    val id: Int
    private var modelPath: String = ""
}
