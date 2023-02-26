package com.joasvpereira.settings.data

import android.content.Context
import com.joasvpereira.settings.R
import pt.joasvpereira.core.settings.domain.data.ThemePreference

object ThemeModeMapper {
    fun mapToString(context: Context, themeMode: ThemePreference.ThemeMode): String {
        return when (themeMode) {
            ThemePreference.ThemeMode.DEFAULT -> context.getString(R.string.theme_mode_default_lable)
            ThemePreference.ThemeMode.LIGHT -> context.getString(R.string.theme_mode_light_lable)
            ThemePreference.ThemeMode.DARK -> context.getString(R.string.theme_mode_dark_lable)
        }
    }

    fun mapFromString(context: Context, themeModeLabel: String): ThemePreference.ThemeMode {
        return when (themeModeLabel) {
            context.getString(R.string.theme_mode_default_lable) -> ThemePreference.ThemeMode.DEFAULT
            context.getString(R.string.theme_mode_light_lable) -> ThemePreference.ThemeMode.LIGHT
            context.getString(R.string.theme_mode_dark_lable) -> ThemePreference.ThemeMode.DARK
            else -> throw ThemeModeNotFoundException("No theme mode for label \"$themeModeLabel\" note found.")
        }
    }
}
