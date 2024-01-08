package ua.honchar.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val name: String,
    val login: String
)
