package com.joasvpereira.settings.compose.main

import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import com.joasvpereira.settings.compose.main.session.SessionSettingsSection
import com.joasvpereira.settings.compose.main.theme.ThemeSettingsSection
import pt.joasvpereira.core.domain.data.SessionItem
import pt.joasvpereira.core.repository.local.entities.Session
import pt.joasvpereira.core.settings.domain.data.ThemePreference
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.ThemeOption
import pt.joasvpereira.coreui.preview.ThemesProvider
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.scaffold.AppScaffold
import pt.joasvpereira.coreui.scaffold.ToolBarConfig

fun ThemePreference.ThemeMode.getId(): Int = when (this) {
    ThemePreference.ThemeMode.DEFAULT -> 0
    ThemePreference.ThemeMode.LIGHT -> 1
    ThemePreference.ThemeMode.DARK -> 2
}

@Composable
fun SettingsMainView(
    onBackClick: () -> Unit,
    currentSessionItem: SessionItem,
    isKeepSession: Boolean,
    onKeepSessionChange: (Boolean) -> Unit,
    onEditProfile: () -> Unit,
    hasMaterialYou: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,
    isMaterialYouEnabled: Boolean,
    onMaterialYouSwitchChange: (Boolean) -> Unit,
    themeModeSelectedOption: ThemePreference.ThemeMode,
    onThemeModeChange: (ThemePreference.ThemeMode) -> Unit
) {
    AppScaffold(
        toolBarConfig = ToolBarConfig(
            title = "Settings",
            onLeftIconClick = onBackClick
        ),
        shouldUseBackgroundImage = false
    ) {
        Column {
            SimpleSpace(size = 20.dp)
            SessionSettingsSection(
                modifier = Modifier,
                currentSession = currentSessionItem,
                isKeepSession = isKeepSession,
                onKeepSessionChange = onKeepSessionChange,
                onEditProfile = onEditProfile,
            )
            SimpleSpace(size = 20.dp)
            ThemeSettingsSection(
                modifier = Modifier,
                hasMaterialYou = hasMaterialYou,
                isMaterialYouEnabled = isMaterialYouEnabled,
                onMaterialYouSwitchChange = onMaterialYouSwitchChange,
                themeModeSelectedOption = themeModeSelectedOption.getId(),
                onThemeModeChange = {
                    // TODO: this need to be refactor
                    onThemeModeChange(
                        when (it) {
                            1 -> ThemePreference.ThemeMode.LIGHT
                            2 -> ThemePreference.ThemeMode.DARK
                            else -> ThemePreference.ThemeMode.DEFAULT
                        }
                    )
                }
            )
        }
    }
}

@Preview(group = "Single")
@Composable
fun SettingsMainViewPreview() {
    DynamicTheme() {
        SettingsMainView(
            onBackClick = {},
            currentSessionItem = SessionItem(id = 1, name = "HOME 2", image = null),
            isKeepSession = true,
            onKeepSessionChange = {},
            onEditProfile = {},
            hasMaterialYou = true,
            isMaterialYouEnabled = false,
            onMaterialYouSwitchChange = {},
            themeModeSelectedOption = ThemePreference.ThemeMode.LIGHT,
            onThemeModeChange = {},
        )
    }
}

@UiModePreview
@Composable
fun SettingsMainViewPreview(@PreviewParameter(ThemesProvider::class) theme: ThemeOption) {
    DynamicTheme(theme) {
        SettingsMainView(
            onBackClick = {},
            currentSessionItem = SessionItem(id = 1, name = "HOME 2", image = null),
            isKeepSession = true,
            onKeepSessionChange = {},
            onEditProfile = {},
            hasMaterialYou = true,
            isMaterialYouEnabled = false,
            onMaterialYouSwitchChange = {},
            themeModeSelectedOption = ThemePreference.ThemeMode.LIGHT,
            onThemeModeChange = {},
        )
    }
}