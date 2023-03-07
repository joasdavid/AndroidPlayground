package com.joasvpereira.settings.compose.main

import android.os.Build
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import com.joasvpereira.settings.compose.main.session.SessionSettingsSection
import com.joasvpereira.settings.compose.main.theme.ThemeSettingsSection
import pt.joasvpereira.core.domain.data.SessionItem
import pt.joasvpereira.core.settings.domain.data.ThemePreference
import pt.joasvpereira.coreui.preview.ThemesProvider
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.scaffold.AppScaffold
import pt.joasvpereira.coreui.scaffold.ToolBarConfig
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.theme.ThemeOption


private const val BOTTOM_MARGIN = 5
@Composable
fun SettingsMainView(
    onBackClick: () -> Unit,
    currentSessionItem: SessionItem,
    isKeepSession: Boolean,
    onKeepSessionChange: (Boolean) -> Unit,
    onEditProfile: () -> Unit,
    onLogout: () -> Unit,
    hasMaterialYou: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,
    isMaterialYouEnabled: Boolean,
    onMaterialYouSwitchChange: (Boolean) -> Unit,
    themeModeSelectedOption: ThemePreference.ThemeMode,
    onThemeModeChange: (ThemePreference.ThemeMode) -> Unit,
) {
    AppScaffold(
        toolBarConfig = ToolBarConfig(
            title = "Settings",
            onLeftIconClick = onBackClick,
        ),
        shouldUseBackgroundImage = false,
    ) {
        LazyColumn {
            item { SimpleSpace(size = 20.dp) }

            item {
                SessionSettingsSection(
                    modifier = Modifier,
                    currentSession = currentSessionItem,
                    isKeepSession = isKeepSession,
                    onKeepSessionChange = onKeepSessionChange,
                    onEditProfile = onEditProfile,
                    onLogout = onLogout,
                )
            }

            item { SimpleSpace(size = 20.dp) }

            item {
                ThemeSettingsSection(
                    modifier = Modifier,
                    hasMaterialYou = hasMaterialYou,
                    isMaterialYouEnabled = isMaterialYouEnabled,
                    onMaterialYouSwitchChange = onMaterialYouSwitchChange,
                    themeModeSelectedOption = themeModeSelectedOption,
                    onThemeModeChange = onThemeModeChange,
                )
            }

            item {
                Spacer(
                    modifier = Modifier.height(
                        BOTTOM_MARGIN.dp + it.calculateBottomPadding()
                    )
                )
            }
        }
    }
}

@Preview(group = "Single")
@Composable
fun SettingsMainViewPreview() {
    DynamicTheme {
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
            onLogout = {},
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
            themeModeSelectedOption = ThemePreference.ThemeMode.DEFAULT,
            onThemeModeChange = {},
            onLogout = {},
        )
    }
}
