package com.joasvpereira.main.compose.dashboard

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.joasvpereira.lib.compose.spacer.HorizontalSpace
import com.joasvpereira.lib.compose.undermenuscaffold.SlideDirection
import com.joasvpereira.lib.compose.undermenuscaffold.UnderMenuScaffold
import com.joasvpereira.lib.compose.undermenuscaffold.rememberUnderMenuScaffold
import com.joasvpereira.main.domain.data.DashboardDivision
import com.joasvpereira.main.presentation.icons.DivisionIcons
import pt.joasvpereira.coreui.preview.FoldablePreview
import pt.joasvpereira.coreui.preview.LargePreview
import pt.joasvpereira.coreui.preview.PreviewWrapperWithTheme
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.scaffold.AppScaffold
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.util.WindowSizeHelper

@Composable
internal fun DashboardScreen(
    isLoading: Boolean,
    sessionName: String,
    sessionImage: Bitmap?,
    divisions: List<DashboardDivision>,
    onDivisionClick: (DashboardDivision) -> Unit,
    onEditClick: (DashboardDivision) -> Unit,
    onAddNewItemClick: () -> Unit,
    onSettingClicked: () -> Unit,
) {
    val state by rememberUnderMenuScaffold(direction = SlideDirection.LEFT)
    if (WindowSizeHelper.currentWidthSize() == WindowSizeHelper.WidthSize.Expanded){
        AppScaffold(
            isTinted = false,
            isLoading = isLoading,
            paddingValues = PaddingValues(0.dp)
        ) {
            Row(Modifier.fillMaxSize()) {
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                ) {

                    val view = LocalView.current
                    DashboardHeader(
                        sessionName = sessionName,
                        sessionImage = sessionImage,
                        //onSettingClicked = onSettingClicked,
                        onSettingClicked = {
                            state.openMenu(view)
                        },
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    DashboardDivisionsSection(
                        divisions = divisions,
                        onDivisionClick = onDivisionClick,
                        onAddNewItemClick = onAddNewItemClick,
                        onEditClick = onEditClick,
                    )
                }
                HorizontalSpace(width = 20.dp)
                MainMenuCompact(
                    sessionName = sessionName,
                    sessionImage = sessionImage,
                    onLogout = onSettingClicked,
                    contentPadding = it,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(250.dp)
                        .clip(RoundedCornerShape(topStart = 50.dp))
                        .shadow(elevation = 10.dp, shape = RoundedCornerShape(topStart = 50.dp)),
                )
            }
        }
    } else {
        UnderMenuScaffold(
            menuContent = {
                MainMenuCompact(
                    sessionName = sessionName,
                    sessionImage = sessionImage,
                    onLogout = onSettingClicked,
                    contentPadding = it,
                    modifier = Modifier.fillMaxSize(),
                )
            },
            state = state
        ) {
            AppScaffold(
                isTinted = false,
                isLoading = isLoading,
            ) {
                Column {

                    val view = LocalView.current
                    DashboardHeader(
                        sessionName = sessionName,
                        sessionImage = sessionImage,
                        //onSettingClicked = onSettingClicked,
                        onSettingClicked = {
                            state.openMenu(view)
                        },
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    DashboardDivisionsSection(
                        divisions = divisions,
                        onDivisionClick = onDivisionClick,
                        onAddNewItemClick = onAddNewItemClick,
                        onEditClick = onEditClick,
                    )
                }
            }
        }
    }
}

@SuppressLint("UseCompatLoadingForDrawables")
@UiModePreview
@LargePreview
@FoldablePreview
@Composable
private fun DashboardScreenPreview() {
    PreviewWrapperWithTheme {
        val d = LocalContext.current.getDrawable(DivisionIcons.cactus.resId)?.toBitmap()
        DashboardScreen(
            isLoading = false,
            sessionName = "Joås V. Pereira",
            sessionImage = d,
            divisions = PreviewData.divisions,
            onDivisionClick = {},
            onAddNewItemClick = {},
            onEditClick = {},
            onSettingClicked = {},
        )
    }
}
