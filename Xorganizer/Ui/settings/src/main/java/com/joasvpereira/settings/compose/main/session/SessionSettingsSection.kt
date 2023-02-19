package com.joasvpereira.settings.compose.main.session

import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdUnits
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import com.joasvpereira.settings.compose.main.SettingsSection
import com.joasvpereira.settings.compose.main.menu.entry.EntryClickable
import com.joasvpereira.settings.compose.main.menu.entry.EntryWithSwitch
import pt.joasvpereira.core.domain.data.SessionItem
import pt.joasvpereira.core.ext.toBase64String
import pt.joasvpereira.core.ext.toBitmap
import pt.joasvpereira.core.repository.CurrentSession
import pt.joasvpereira.core.repository.local.entities.Session
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.R
import pt.joasvpereira.coreui.preview.UiModePreview

@Composable
internal fun SessionSettingsSection(
    modifier: Modifier = Modifier,
    currentSession : SessionItem,
    isKeepSession : Boolean,
    onKeepSessionChange : (Boolean) -> Unit,
    onEditProfile : () -> Unit,
) {
    SettingsSection(
        modifier = modifier,
        sectionName = "Session"
    ) {
        SimpleSpace(size = 20.dp)
        SettingsSessionIndicator(currentSession = currentSession)

        SimpleSpace(size = 20.dp)

        EntryWithSwitch(
            text = "Should keep session",
            description = "When turn on will keep the session even after the app is killed, this mean that the app will login with the same profile that was selected whe the app was closed.",
            checked = isKeepSession,
            onCheckedChange = onKeepSessionChange
        )

        SimpleSpace(size = 20.dp)

        EntryClickable(text = "Edit my profile", onClick = onEditProfile)

        SimpleSpace(size = 20.dp)
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
                )
        }
    }
}