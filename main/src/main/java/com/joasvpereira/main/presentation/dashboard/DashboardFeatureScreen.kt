package com.joasvpereira.main.presentation.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import com.joasvpereira.main.compose.dashboard.DashboardScreen
import pt.joasvpereira.coreui.dialog.DialogWithTwoButton
import pt.joasvpereira.coreui.text.field.AppTextField

@Composable
fun DashboardFeatureScreen(
    viewModel: DashboardFeatureScreenViewModel,
    navController: NavController? = null,
    onSettingsClicked: () -> Unit,
) {
    DashboardScreen(
        isLoading = viewModel.state.isLoading,
        sessionName = viewModel.state.sessionName,
        sessionImage = viewModel.state.sessionImage,
        divisions = viewModel.state.divisions,
        onDivisionClick = { navController?.navigate("DivisionFeatureScreen?id=${it.id}") },
        onAddNewItemClick = { navController?.navigate("CreateDivisionsFeatureScreen") },
        onEditClick = { navController?.navigate("CreateDivisionsFeatureScreen?id=${it.id}") },
        onSettingClicked = onSettingsClicked,
    )
}
