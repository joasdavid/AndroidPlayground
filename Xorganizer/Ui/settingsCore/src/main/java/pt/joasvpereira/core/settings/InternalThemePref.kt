package pt.joasvpereira.core.settings

import pt.joasvpereira.core.settings.domain.data.ThemePreference

object InternalThemePref {
    var mode: ThemePreference.ThemeMode = ThemePreference.ThemeMode.DEFAULT
    var isMaterialYou : Boolean = true
}