package ua.honchar.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LessonDTO(
    val id: Int,
    val name: String,
    val lessonParts: List<LessonPartDTO>
)
