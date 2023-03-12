package com.joasvpereira.main.compose.dashboard

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.joasvpereira.main.presentation.icons.DivisionIcons
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.session.SessionIconHolder
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.main.R

@Composable
internal fun DashboardHeader(
    sessionName: String,
    sessionImage: Bitmap?,
    onSettingClicked: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        RowTitleAndSettings(onSettingClicked)

        Spacer(modifier = Modifier.height(28.5.dp))
        RowUserInfo(sessionName, sessionImage)
    }
}

@Composable
private fun RowUserInfo(sessionName: String, sessionImage: Bitmap?) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.dashboard_welcome),
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                text = sessionName,
                style = MaterialTheme.typography.labelLarge,
            )
        }
        Box(
            Modifier
                .align(Alignment.Bottom)
                .size(40.dp)
                .background(MaterialTheme.colorScheme.primaryContainer, shape = CircleShape),
        ) {
            SessionIconHolder(
                sessionName = sessionName,
                sessionImage = sessionImage,
            )
        }
    }
}

@Composable
private fun RowTitleAndSettings(onSettingClicked: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "XOrganizer",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.weight(1f),
        )
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onSettingClicked() },
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(24.dp),
                imageVector = Icons.Default.Settings,
                contentDescription = stringResource(R.string.settings_content_description),
            )
        }
    }
}

@SuppressLint("UseCompatLoadingForDrawables")
@UiModePreview
@Composable
private fun DashboardHeaderPreview() {
    val d = LocalContext.current.getDrawable(DivisionIcons.cactus.resId)?.toBitmap()
    DynamicTheme {
        Surface {
            DashboardHeader(
                sessionName = "Joås V. Pereira",
                sessionImage = d,
                onSettingClicked = {},
            )
        }
    }
}
