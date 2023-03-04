package com.joasvpereira.main.presentation.create

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.ThemeSelector
import com.joasvpereira.main.compose.create.DivisionIconSelector
import com.joasvpereira.main.compose.create.DivisionNameAndDescription
import pt.joasvpereira.coreui.dialog.DialogWithTwoButton
import pt.joasvpereira.coreui.scaffold.AppScaffold
import pt.joasvpereira.coreui.scaffold.ToolBarConfig
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
        AppScaffold(
            isLoading = viewModel.state.isLoading,
            toolBarConfig = ToolBarConfig(
                title = "",
                onRightIconClick = {
                    navController?.popBackStack()
                },
            ),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                SimpleSpace(size = 100.dp)
                DivisionIconSelector(modifier = Modifier.fillMaxWidth(.75f), iconSelected = viewModel.state.icon, onIconSelected = viewModel::iconSelected)
                Spacer(modifier = Modifier.size(20.dp))
                DivisionNameAndDescription(
                    name = viewModel.state.name,
                    onNameChange = viewModel::nameChanged,
                    description = viewModel.state.description,
                    onDescriptionChange = viewModel::descriptionChanged,
                )
                Spacer(modifier = Modifier.size(20.dp))
                ThemeSelector(
                    selectedOption = viewModel.state.theme,
                    onThemeChosen = viewModel::themeSelected,
                )
                SimpleSpace(size = 45.dp)
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = viewModel::save,
                ) {
                    if (viewModel.state.mode == Mode.CREATE) {
                        Text(text = "Save")
                    } else {
                        Text(text = "Update")
                    }
                }
                if (viewModel.state.mode == Mode.EDIT) {
                    SimpleSpace(size = 10.dp)
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                        onClick = viewModel::showDeletePopup,
                    ) {
                        Text(text = "Delete")
                    }
                }
            }
        }
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

