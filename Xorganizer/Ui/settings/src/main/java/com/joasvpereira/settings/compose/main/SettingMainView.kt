package com.joasvpereira.settings.compose.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import com.joasvpereira.settings.compose.main.session.SessionSettingsSection
import pt.joasvpereira.core.repository.local.entities.Session
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.ThemeOption
import pt.joasvpereira.coreui.preview.ThemesProvider
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.scaffold.AppScaffold
import pt.joasvpereira.coreui.scaffold.ToolBarConfig

@Composable
fun SettingsMainView(
    onBackClick : () -> Unit,
) {
    AppScaffold(
        toolBarConfig = ToolBarConfig(
            title = "Settings",
            onLeftIconClick = onBackClick
        ),
        shouldUseBackgroundImage = false
    ) {
        SimpleSpace(size = 10.dp)
        SessionSettingsSection(currentSession = Session(id = 1, displayName = "HOME 2", image = null))
    }
}

@UiModePreview
@Composable
fun SettingsMainViewPreview(@PreviewParameter(ThemesProvider::class) theme: ThemeOption) {
    DynamicTheme(theme) {
        SettingsMainView(onBackClick = {})
    }
}