package com.joasvpereira.main.compose.dashboard

import android.graphics.Bitmap
import android.opengl.Visibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import com.joasvpereira.main.presentation.icons.DivisionIcons
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.main.R

@Composable
internal fun DashboardHeader(
    sessionName: String,
    sessionImage: Bitmap?,
    onSettingOptionClicked: (Int) -> Unit
) {
    var settingsOpen by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "XOrganizer",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.weight(1f)
            )
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                .clickable { settingsOpen = true }
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(24.dp),
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings"
                )
                DropdownMenu(expanded = settingsOpen, onDismissRequest = { settingsOpen = false }) {
                    HeaderSettingsOptions(
                        iconPainter = rememberVectorPainter(image = Icons.Default.Refresh),
                        text = "Sync dashboard",
                        onClick = {
                            settingsOpen = false
                            onSettingOptionClicked(0)
                        }
                    )
                }
            }

        }

        Spacer(modifier = Modifier.height(28.5.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Welcome", style = MaterialTheme.typography.headlineSmall)
                Text(text = sessionName, style = MaterialTheme.typography.labelLarge)
            }
            Box(
                Modifier
                    .align(Alignment.Bottom)
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer, shape = CircleShape)
            ) {
                sessionImage.let {
                    if (it == null) {
                        Icon(
                            painter = painterResource(id = R.drawable.usericon),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = .5f),
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(40.dp)
                        )
                    } else {
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = "",
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(40.dp)
                                .clip(CircleShape)
                                .border(1.dp, color = MaterialTheme.colorScheme.primaryContainer, shape = CircleShape)
                        )
                    }
                }
            }
        }
    }
}

@Composable
internal fun ColumnScope.HeaderSettingsOptions(
    iconPainter: Painter,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        SimpleSpace(size = 2.dp)
        Icon(painter = iconPainter, contentDescription = null)
        SimpleSpace(size = 6.dp)
        Text(text = text.uppercase(), maxLines = 1, modifier = Modifier.weight(1f))
        SimpleSpace(size = 6.dp)
    }
}

@UiModePreview
@Composable
fun HeaderSettingsOptionsPreview() {
    DynamicTheme {
        Surface(modifier = Modifier.width(IntrinsicSize.Max)) {
            Column {
                HeaderSettingsOptions(iconPainter = rememberVectorPainter(image = Icons.Default.Refresh), text = "Sync dashboard") {}
                HeaderSettingsOptions(iconPainter = rememberVectorPainter(image = Icons.Default.Warning), text = "Text") {}
            }
        }
    }
}

@UiModePreview
@Composable
private fun DashboardHeaderPreview() {
    val d = LocalContext.current.getDrawable(DivisionIcons.cactus.resId)?.toBitmap()
    DynamicTheme {
        Surface {
            DashboardHeader(
                sessionName = "Joås V. Pereira",
                sessionImage = d,
                onSettingOptionClicked = {},
            )
        }
    }
}