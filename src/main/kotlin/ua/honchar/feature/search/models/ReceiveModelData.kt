package ua.honchar.feature.search.models

import kotlinx.serialization.Serializable

@Serializable
data class ReceiveModelData(
    val categoryId: Int? = null,
    val langId: Int? = null
)
