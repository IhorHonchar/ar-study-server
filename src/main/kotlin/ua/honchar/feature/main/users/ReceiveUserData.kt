package ua.honchar.feature.main.users

import kotlinx.serialization.Serializable

@Serializable
data class ReceiveUserData(
    val login: String? = null,
    val pass: String? = null,
    val name: String? = null
)