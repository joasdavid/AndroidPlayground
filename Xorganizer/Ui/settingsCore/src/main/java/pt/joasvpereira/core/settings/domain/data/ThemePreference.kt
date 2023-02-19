package pt.joasvpereira.core.settings.domain.data

data class ThemePreference(
    val isMaterialYouEnabled : Boolean,
    val mode : ThemeMode
) {
    enum class ThemeMode {
        DEFAULT,
        LIGHT,
        DARK
    }
}