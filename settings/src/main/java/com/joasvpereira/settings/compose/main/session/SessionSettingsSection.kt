package com.joasvpereira.settings.compose.main.session

import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.joasvpereira.lib.compose.spacer.SimpleSpace
import com.joasvpereira.settings.compose.main.SettingsSection
import com.joasvpereira.settings.compose.main.menu.entry.EntryClickable
import com.joasvpereira.settings.compose.main.menu.entry.EntryWithSwitch
import pt.joasvpereira.core.domain.data.SessionItem
import pt.joasvpereira.coreui.R
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.theme.DynamicTheme

@Composable
internal fun SessionSettingsSection(
    modifier: Modifier = Modifier,
    currentSession: SessionItem,
    isKeepSession: Boolean,
    onKeepSessionChange: (Boolean) -> Unit,
    onEditProfile: () -> Unit,
    onLogout: () -> Unit,
) {
    SettingsSection(
        modifier = modifier,
        sectionName = stringResource(com.joasvpereira.settings.R.string.session_section_title),
    ) {
        SimpleSpace(size = 20.dp)
        SettingsSessionIndicator(currentSession = currentSession)

        SimpleSpace(size = 20.dp)

        EntryWithSwitch(
            text = stringResource(com.joasvpereira.settings.R.string.label_keep_session),
            description = stringResource(com.joasvpereira.settings.R.string.description_keep_session),
            checked = isKeepSession,
            onCheckedChange = onKeepSessionChange,
        )

        SimpleSpace(size = 20.dp)

        EntryClickable(
            text = stringResource(com.joasvpereira.settings.R.string.edit_my_profile),
            onClick = onEditProfile,
        )

        SimpleSpace(size = 20.dp)

        EntryClickable(
            text = stringResource(com.joasvpereira.settings.R.string.logout),
            onClick = onLogout,
            icon = rememberVectorPainter(image = Icons.Default.Logout),
        )

        SimpleSpace(size = 20.dp)
    }
}

@Preview(group = "Single")
@Composable
private fun SessionSettingsSectionSinglePreview() {
    DynamicTheme {
        Surface(modifier = Modifier) {
            SessionSettingsSection(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 20.dp)
                    .fillMaxWidth(),
                currentSession = SessionItem(
                    id = 1,
                    name = "Joás V. Pereira",
                ),
                isKeepSession = true,
                onKeepSessionChange = {},
                onEditProfile = {},
                onLogout = {},
            )
        }
    }
}

@UiModePreview
@Composable
private fun SessionSettingsSectionPreview() {
    DynamicTheme {
        val context = LocalContext.current
        val drawable = AppCompatResources.getDrawable(context, R.drawable.ic_box_3)
        Surface(modifier = Modifier) {
            SessionSettingsSection(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 20.dp)
                    .fillMaxWidth(),
                currentSession = SessionItem(id = 1, name = "Joás V. Pereira", image = drawable?.toBitmap()),
                isKeepSession = true,
                onKeepSessionChange = {},
                onEditProfile = {},
                onLogout = {},
            )
        }
    }
}
