package ua.honchar.feature.main.lessons

import kotlinx.serialization.Serializable

@Serializable
data class ReceiveLessonData(
    val moduleId: Int? = null,
    val langId: Int? = null
)
