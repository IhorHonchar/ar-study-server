package ua.honchar.feature.main.modules

import kotlinx.serialization.Serializable

@Serializable
data class ReceiveModuleData(
    val categoryId: Int? = null,
    val langId: Int? = null
)
