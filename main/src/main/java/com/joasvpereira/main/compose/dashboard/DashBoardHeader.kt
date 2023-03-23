package com.joasvpereira.main.compose.dashboard

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.drawable.toBitmap
import com.joasvpereira.lib.compose.spacer.HorizontalSpace
import com.joasvpereira.main.presentation.icons.DivisionIcons
import pt.joasvpereira.coreui.preview.FoldablePreview
import pt.joasvpereira.coreui.preview.LargePreview
import pt.joasvpereira.coreui.preview.PreviewWrapperWithTheme
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.session.SessionIconHolder
import pt.joasvpereira.coreui.util.WindowSizeHelper
import pt.joasvpereira.main.R

@Composable
internal fun DashboardHeader(
    sessionName: String,
    sessionImage: Bitmap?,
    onSettingClicked: () -> Unit,
    isExpanded: Boolean = WindowSizeHelper.currentWidthSize() == WindowSizeHelper.WidthSize.Expanded,
) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (
            titleRef,
            sessionRef,
            settingsRef,
        ) = createRefs()

        AppTitle(
            modifier = Modifier
                .constrainAs(titleRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
        )

        /*ProfileIndicator(
            sessionName = sessionName,
            sessionImage = sessionImage,
            isExpanded = isExpanded,
            modifier = Modifier.constrainAs(sessionRef) {
                end.linkTo(parent.end)
            },
        )*/

        SettingsButton(
            modifier = Modifier
                .constrainAs(settingsRef) {
                    top.linkTo(titleRef.top)
                    bottom.linkTo(titleRef.bottom)
                    end.linkTo(parent.end)
                    //start.linkTo(sessionRef.start)
                },
            isExpanded,
            onSettingClicked,
        )
    }
}

@Composable
fun SettingsButton(modifier: Modifier, isExpanded: Boolean, onSettingClicked: () -> Unit) {
    if (isExpanded) {
        Button(
            modifier = modifier,
            onClick = onSettingClicked,
        ) {
            Row {
                Text(text = "Settings")
                HorizontalSpace(width = 10.dp)
                Icon(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(24.dp),
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(R.string.settings_content_description),
                )
            }
        }
    } else {
        Icon(
            modifier = modifier
                .clip(CircleShape)
                .size(24.dp)
                .clickable { onSettingClicked() },
            imageVector = Icons.Default.Menu,
            contentDescription = stringResource(R.string.settings_content_description),
        )
    }
}

@Composable
fun ProfileIndicator(sessionName: String, sessionImage: Bitmap?, isExpanded: Boolean, modifier: Modifier = Modifier) {
    if (isExpanded) {
        ProfileIndicatorExpanded(modifier = modifier, sessionName = sessionName, sessionImage = sessionImage)
    } else {
        ProfileLetterOrImageIndicator(modifier = modifier, sessionName = sessionName, sessionImage = sessionImage)
    }
}

@Composable
fun ProfileIndicatorExpanded(sessionName: String, sessionImage: Bitmap?, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = sessionName,
            style = MaterialTheme.typography.labelLarge,
        )
        HorizontalSpace(width = 5.dp)
        ProfileLetterOrImageIndicator(sessionName = sessionName, sessionImage = sessionImage)
    }
}

@Composable
private fun ProfileLetterOrImageIndicator(sessionName: String, sessionImage: Bitmap?, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer, shape = CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        SessionIconHolder(
            sessionName = sessionName,
            sessionImage = sessionImage,
        )
    }
}

@Composable
fun AppTitle(modifier: Modifier) {
    Text(
        modifier = modifier,
        text = "XOrganizer",
        style = MaterialTheme.typography.headlineLarge,
    )
}

@SuppressLint("UseCompatLoadingForDrawables")
@UiModePreview
@FoldablePreview
@LargePreview
@Composable
private fun DashboardHeaderPreview() {
    val d = LocalContext.current.getDrawable(DivisionIcons.cactus.resId)?.toBitmap()
    PreviewWrapperWithTheme {
        Surface {
            DashboardHeader(
                sessionName = "Joås V. Pereira",
                sessionImage = d,
                onSettingClicked = {},
            )
        }
    }
}
