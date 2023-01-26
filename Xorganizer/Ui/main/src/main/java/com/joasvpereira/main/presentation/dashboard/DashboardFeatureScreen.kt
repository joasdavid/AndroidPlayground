package com.joasvpereira.main.presentation.dashboard

import androidx.compose.material.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.joasvpereira.main.compose.dashboard.DashboardScreen
import pt.joasvpereira.coreui.dialog.SimpleDialog

@Composable
fun DashboardFeatureScreen(
    viewModel: DashboardFeatureScreenViewModel,
    navController: NavController? = null
) {
    var showDialog by remember {
        mutableStateOf(true)
    }
    if (showDialog)
    SimpleDialog(onDismissRequest = { showDialog = false }, confirmationText = "Close", onConfirmationClick = { }) {
        Text(text = "No Profile found . . .")
    }
    DashboardScreen(
        isLoading = viewModel.state.isLoading,
        sessionName = viewModel.state.sessionName,
        sessionImage = viewModel.state.sessionImage,
        divisions = viewModel.state.divisions,
        onDivisionClick = {},
        onAddNewItemClick = {},
    )
}