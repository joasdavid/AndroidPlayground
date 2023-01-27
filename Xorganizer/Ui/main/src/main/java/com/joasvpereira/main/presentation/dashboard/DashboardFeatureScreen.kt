package com.joasvpereira.main.presentation.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
    navController: NavController? = null
) {
    DeletePopup(viewModel)
    DashboardScreen(
        isLoading = viewModel.state.isLoading,
        sessionName = viewModel.state.sessionName,
        sessionImage = viewModel.state.sessionImage,
        divisions = viewModel.state.divisions,
        onDivisionClick = {},
        onAddNewItemClick = {},
        onEditClick = {},
        onDeleteClick = {
         viewModel.askToDelete(it)
        },
    )
}

@Composable
private fun DeletePopup(viewModel: DashboardFeatureScreenViewModel) {
    val deleteEvent = viewModel.state.deleteEvent
    AnimatedVisibility(visible = deleteEvent != null) {
        deleteEvent?.run {
            DialogWithTwoButton(
                onDismissRequest = { viewModel.cancelDelete() },
                indicatorIcon = Icons.Default.Delete,
                indicatorColor = MaterialTheme.colorScheme.error,
                buttonPositiveText = "DELETE",
                buttonPositiveColor = MaterialTheme.colorScheme.error,
                isButtonPositiveEnabled = deleteEvent.isNameMatchingConfirmation ?: false,
                onButtonPositiveClick = { viewModel.deleteDivision() },
                buttonNegativeText = "CANCEL",
                buttonNegativeColor = MaterialTheme.colorScheme.surfaceVariant,
                onButtonNegativeClick = { viewModel.cancelDelete() }) {

                val nameUppercase = deleteEvent.division.name.toUpperCase(Locale.current)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "To delete division write it's name in all caps \n \"$nameUppercase", textAlign = TextAlign.Center)
                    SimpleSpace(size = 20.dp)
                    AppTextField(
                        value = deleteEvent.confirmationText,
                        onValueChange = { viewModel.askToDelete(deleteEvent.division, it) },
                        placeholder = "",
                        keyboardOptions = KeyboardOptions(autoCorrect = false, imeAction = ImeAction.Send),
                        keyboardActions = KeyboardActions(onSend = {viewModel.deleteDivision()})
                    )
                    SimpleSpace(size = 20.dp)
                }
            }
        }
    }
}