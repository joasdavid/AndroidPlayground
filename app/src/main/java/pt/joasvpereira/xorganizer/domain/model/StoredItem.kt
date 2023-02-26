package pt.joasvpereira.xorganizer.domain.model

data class StoredItem(
    val id: Int,
    val name: String,
    val description: String,
    val tags: List<String>,
    val isUsed: Boolean,
)
