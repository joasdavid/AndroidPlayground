package com.joasvpereira.settings.compose.main.session

import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import pt.joasvpereira.core.domain.data.SessionItem
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.R

@Composable
fun SettingsSessionIndicator(
    modifier: Modifier = Modifier,
    currentSession : SessionItem
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        currentSession.image.let {
            if (it == null) {
                Spacer(
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = .5f),
                            shape = CircleShape
                        )
                        .size(40.dp)
                )
            } else {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(1.dp, color = MaterialTheme.colorScheme.primaryContainer, shape = CircleShape),
                )
            }
        }
        SimpleSpace(size = 10.dp)
        Text(text = currentSession.name)
    }
}

@Preview
@Composable
private fun SettingsSessionIndicatorPreview() {

    val context = LocalContext.current
    val drawable = AppCompatResources.getDrawable(context, R.drawable.ic_upload)
    DynamicTheme() {
        Surface {
            SettingsSessionIndicator(
                currentSession = SessionItem(id = 1, name = "Joás V. Pereira", image = drawable?.toBitmap())
            )
        }
    }
}