package com.joasvpereira.main.compose.dashboard

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.joasvpereira.main.domain.data.DashboardDivision
import com.joasvpereira.main.presentation.icons.DivisionIcons
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.scaffold.AppScaffold
import pt.joasvpereira.coreui.theme.DynamicTheme

@Composable
internal fun DashboardScreen(
    isLoading: Boolean,
    sessionName: String,
    sessionImage: Bitmap?,
    divisions: List<DashboardDivision>,
    onDivisionClick: (DashboardDivision) -> Unit,
    onEditClick: (DashboardDivision) -> Unit,
    onDeleteClick: (DashboardDivision) -> Unit,
    onAddNewItemClick: () -> Unit,
    onSettingClicked: () -> Unit,
) {
    AppScaffold(
        isTinted = false,
        isLoading = isLoading,
    ) {
        DashboardHeader(
            sessionName = sessionName,
            sessionImage = sessionImage,
            onSettingClicked = onSettingClicked,
        )
        Spacer(modifier = Modifier.size(20.dp))
        DashboardDivisionsSection(
            divisions = divisions,
            onDivisionClick = onDivisionClick,
            onAddNewItemClick = onAddNewItemClick,
            onEditClick = onEditClick,
            onDeleteClick = onDeleteClick,
        )
    }
}

@UiModePreview
@Composable
private fun DashboardScreenPreview() {
    DynamicTheme {
        val d = LocalContext.current.getDrawable(DivisionIcons.cactus.resId)?.toBitmap()
        DashboardScreen(
            isLoading = false,
            sessionName = "Joås V. Pereira",
            sessionImage = d,
            divisions = PreviewData.divisions,
            onDivisionClick = {},
            onAddNewItemClick = {},
            onEditClick = {},
            onDeleteClick = {},
            onSettingClicked = {},
        )
    }
}
