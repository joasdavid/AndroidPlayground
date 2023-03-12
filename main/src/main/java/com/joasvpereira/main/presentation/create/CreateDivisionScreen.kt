package com.joasvpereira.main.presentation.create

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
import androidx.compose.ui.res.stringResource
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
import pt.joasvpereira.main.R
import java.util.Locale

@Composable
fun CreateDivisionScreen(
    divisionId: Int? = null,
    viewModel: CreateDivisionViewModel,
    navController: NavController? = null,
) {
    LaunchedEffect(key1 = viewModel.state.navigation) {
        when (viewModel.state.navigation) {
            CreateDivisionScreenNavigation.Idle -> {
                // do nothing, navigation is idle
            }

            CreateDivisionScreenNavigation.SaveDone -> {
                navController?.popBackStack()
            }

            CreateDivisionScreenNavigation.DeleteDone -> {
                navController?.popBackStack()
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
            buttonPositiveText = stringResource(R.string.general_delete).uppercase(Locale.getDefault()),
            buttonPositiveColor = MaterialTheme.colorScheme.error,
            isButtonPositiveEnabled = deleteEvent.isNameMatchingConfirmation,
            onButtonPositiveClick = { viewModel.deleteDivision() },
            buttonNegativeText = stringResource(R.string.general_cancel).uppercase(Locale.getDefault()),
            buttonNegativeColor = MaterialTheme.colorScheme.surfaceVariant,
            onButtonNegativeClick = { viewModel.hideDeletePopup() },
        ) {
            val nameUppercase = deleteEvent.matchingName.uppercase()
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = stringResource(R.string.popup_delete_message, nameUppercase), textAlign = TextAlign.Center)
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
