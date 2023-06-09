package com.joasvpereira.main.compose.dashboard

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.joasvpereira.lib.compose.spacer.HorizontalSpace
import com.joasvpereira.lib.compose.undermenuscaffold.SlideDirection
import com.joasvpereira.lib.compose.undermenuscaffold.UnderMenuScaffold
import com.joasvpereira.lib.compose.undermenuscaffold.rememberUnderMenuScaffold
import com.joasvpereira.main.compose.dashboard.menu.large.MainMenuLargeScreens
import com.joasvpereira.main.domain.data.DashboardDivision
import com.joasvpereira.main.presentation.icons.DivisionIcons
import pt.joasvpereira.coreui.preview.FoldablePreview
import pt.joasvpereira.coreui.preview.LargePreview
import pt.joasvpereira.coreui.preview.PreviewWrapperWithTheme
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.scaffold.AppScaffold
import pt.joasvpereira.coreui.theme.ThemeOption
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
    // NavigationDrawerItem(label = { /*TODO*/ }, selected = , onClick = { /*TODO*/ })
    val state by rememberUnderMenuScaffold(direction = SlideDirection.LEFT)
    if (!WindowSizeHelper.isWidthCompact) {
        AppScaffold(
            isTinted = false,
            isLoading = isLoading,
            paddingValues = PaddingValues(start = 20.dp, top = 20.dp),
        ) {
            Row(Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                ) {
                    val view = LocalView.current
                    DashboardHeader(
                        sessionName = sessionName,
                        sessionImage = sessionImage,
                        // onSettingClicked = onSettingClicked,
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
                MainMenuLargeScreens(
                    sessionName = sessionName,
                    sessionImage = sessionImage,
                    onLogout = onSettingClicked,
                    contentPadding = it,
                    isCompact = !WindowSizeHelper.isWidthExpanded,
                    color = MaterialTheme.colorScheme.primary,
                    contentColor = contentColorFor(MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .fillMaxHeight()
                        // .fillMaxWidth(.20f)
                        .width(IntrinsicSize.Max)
                        .widthIn(max = 250.dp)
                        .clip(RoundedCornerShape(topStart = 50.dp)),
                )
            }
        }
    } else {
        UnderMenuScaffold(
            menuContent = { padding ->
                MainMenuLargeScreens(
                    sessionName = sessionName,
                    sessionImage = sessionImage,
                    onLogout = onSettingClicked,
                    contentPadding = PaddingValues(
                        top = padding.calculateTopPadding(),
                        bottom = padding.calculateBottomPadding(),
                    ),
                    color = MaterialTheme.colorScheme.primary,
                    contentColor = contentColorFor(MaterialTheme.colorScheme.primary),
                    modifier = Modifier.fillMaxSize(),
                )
            },
            state = state,
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
                        // onSettingClicked = onSettingClicked,
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
    PreviewWrapperWithTheme(ThemeOption.THEME_BLUE) {
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
