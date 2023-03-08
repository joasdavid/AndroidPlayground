package com.joasvpereira.settings.compose.main.session

import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.joasvpereira.lib.compose.spacer.SimpleSpace
import pt.joasvpereira.core.domain.data.SessionItem
import pt.joasvpereira.coreui.R
import pt.joasvpereira.coreui.session.SessionIconHolder
import pt.joasvpereira.coreui.theme.DynamicTheme

@Composable
fun SettingsSessionIndicator(
    modifier: Modifier = Modifier,
    currentSession: SessionItem,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SessionIconHolder(
            sessionName = currentSession.name,
            sessionImage = currentSession.image,
        )
        SimpleSpace(size = 10.dp)
        Text(text = currentSession.name)
    }
}

@Preview
@Composable
private fun SettingsSessionIndicatorPreview() {
    val context = LocalContext.current
    val drawable = AppCompatResources.getDrawable(context, R.drawable.ic_upload)
    DynamicTheme {
        Surface {
            SettingsSessionIndicator(
                currentSession = SessionItem(
                    id = 1,
                    name = "Joás V. Pereira",
                    image = drawable?.toBitmap(),
                ),
            )
        }
    }
}

@Preview
@Composable
private fun SettingsSessionIndicatorNoImagePreview() {
    DynamicTheme {
        Surface {
            SettingsSessionIndicator(
                currentSession = SessionItem(
                    id = 1,
                    name = "Joás V. Pereira",
                ),
            )
        }
    }
}
