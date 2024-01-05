package ua.honchar.domain.model

import kotlinx.serialization.Serializable
import ua.honchar.core.getImagePath

@Serializable
data class CategoryDTO(
    val id: Int,
    val name: String,
    val order: Int
) {
    private var imagePath: String? = null

    fun setCategoryImagePath(imageName: String) {
        imagePath = getImagePath("category/$imageName")
    }
}
