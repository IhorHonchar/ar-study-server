package ua.honchar.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LanguageDTO(
    val id: Int,
    val code: String
)
