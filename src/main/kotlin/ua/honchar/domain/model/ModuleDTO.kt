package ua.honchar.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ModuleDTO(
    val id: Int,
    val name: String,
    val info: String
)
