package ua.honchar.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LessonPartDTO(
    val id: Int,
    val text: String,
    val model: ModelDTO,
)
