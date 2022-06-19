package pt.joasvpereira.xorganizer.domain.model

data class Division(
    val id: Int,
    val name: String,
    val description: String,
    val iconId: Int,
    val nrBox: Int,
    val nrItem: Int,
    val themeId: Int,
)