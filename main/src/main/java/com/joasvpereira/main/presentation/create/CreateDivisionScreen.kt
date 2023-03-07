package com.joasvpereira.main.presentation.create

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joasvpereira.lib.compose.spacer.SimpleSpace
import com.joasvpereira.main.compose.create.CreateDivisionSection
import pt.joasvpereira.coreui.dialog.DialogWithTwoButton
import pt.joasvpereira.coreui.text.field.AppTextField
import pt.joasvpereira.coreui.theme.DynamicTheme

@Composable
fun CreateDivisionScreen(
    divisionId: Int? = null,
    viewModel: CreateDivisionViewModel,
    navController: NavController? = null,
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = viewModel.state.navigation) {
        when (viewModel.state.navigation) {
            CreateDivisionScreenNavigation.Idle -> {
                // do nothing navigation is idle
            }

            CreateDivisionScreenNavigation.SaveDone -> {
                navController?.popBackStack()
                Toast.makeText(context, "SAVED", Toast.LENGTH_SHORT).show()
            }

            CreateDivisionScreenNavigation.DeleteDone -> {
                navController?.popBackStack()
                Toast.makeText(context, "${viewModel.state.name} deleted.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    LaunchedEffect(key1 = divisionId) {
        divisionId?.run {
            viewModel.targetDivision(this)
        }
    }

    DeletePopup(viewModel = viewModel)

    DynamicTheme(viewModel.state.theme) {
        CreateDivisionSection(
            isLoading = viewModel.state.isLoading,
            onIconChange = viewModel::iconSelected,
            name = viewModel.state.name,
            onNameChange = viewModel::nameChanged,
            description = viewModel.state.description,
            onDescriptionChange = viewModel::descriptionChanged,
            onThemeChange = viewModel::themeSelected,
            onSave = viewModel::save,
            onCloseClick = { navController?.popBackStack() },
            canDelete = viewModel.state.mode == Mode.EDIT,
            onDeleteClick = viewModel::showDeletePopup,
        )
    }
}

@Composable
private fun DeletePopup(viewModel: CreateDivisionViewModel) {
    val deleteEvent = viewModel.state.deleteData
    AnimatedVisibility(visible = deleteEvent.isPopupShowing) {
        DialogWithTwoButton(
            onDismissRequest = { viewModel.hideDeletePopup() },
            indicatorIcon = Icons.Default.Delete,
            indicatorColor = MaterialTheme.colorScheme.error,
            buttonPositiveText = "DELETE",
            buttonPositiveColor = MaterialTheme.colorScheme.error,
            isButtonPositiveEnabled = deleteEvent.isNameMatchingConfirmation,
            onButtonPositiveClick = { viewModel.deleteDivision() },
            buttonNegativeText = "CANCEL",
            buttonNegativeColor = MaterialTheme.colorScheme.surfaceVariant,
            onButtonNegativeClick = { viewModel.hideDeletePopup() },
        ) {
            val nameUppercase = deleteEvent.matchingName.uppercase()
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "To delete division write it's name in all caps \n \"$nameUppercase\"", textAlign = TextAlign.Center)
                SimpleSpace(size = 20.dp)
                AppTextField(
                    value = deleteEvent.name,
                    onValueChange = { viewModel.updateConfirmationNameOnDeletePopup(it) },
                    placeholder = "",
                    keyboardOptions = KeyboardOptions(autoCorrect = false, imeAction = ImeAction.Send, capitalization = KeyboardCapitalization.Sentences),
                    keyboardActions = KeyboardActions(onSend = { viewModel.deleteDivision() }),
                )
                SimpleSpace(size = 20.dp)
            }
        }
    }
}
