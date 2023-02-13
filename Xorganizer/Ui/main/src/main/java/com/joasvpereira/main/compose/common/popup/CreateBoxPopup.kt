package com.joasvpereira.main.compose.common.popup

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.dialog.DialogWithTwoButton
import pt.joasvpereira.coreui.text.field.AppTextField
import pt.joasvpereira.main.R

@Composable
fun CreateBoxPopup(
    onDismissRequest : () -> Unit,
    onButtonPositiveClick : () -> Unit,
    onButtonNegativeClick : () -> Unit,
    boxName : String,
    onBoxNameChange: (String) -> Unit,
    description : String,
    onDescriptionChange: (String) -> Unit,
    isOnEditMode : Boolean
) {
    DialogWithTwoButton(
        onDismissRequest = onDismissRequest,
        indicatorIcon = painterResource(id = R.drawable.ic_box_3),
        indicatorColor = MaterialTheme.colorScheme.tertiaryContainer,
        buttonPositiveText = if (isOnEditMode) "Update" else "Save",
        buttonPositiveColor = MaterialTheme.colorScheme.tertiary,
        isButtonPositiveEnabled = boxName.isNotBlank(),
        onButtonPositiveClick = onButtonPositiveClick,
        buttonNegativeText = "Close",
        buttonNegativeColor = MaterialTheme.colorScheme.outline,
        onButtonNegativeClick = onButtonNegativeClick) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (isOnEditMode) {
                Text(text = "Update box".toUpperCase(Locale.current), textAlign = TextAlign.Center, style = MaterialTheme.typography.titleSmall)
            } else {
                Text(text = "Create new box".toUpperCase(Locale.current), textAlign = TextAlign.Center, style = MaterialTheme.typography.titleSmall)
            }
            SimpleSpace(size = 20.dp)
            AppTextField(value = boxName, onValueChange = onBoxNameChange, placeholder = "Box name")
            SimpleSpace(size = 20.dp)
            AppTextField(value = description, onValueChange = onDescriptionChange, placeholder = "Description")
            SimpleSpace(size = 20.dp)
        }
    }
}

@Preview
@Composable
private fun CreateItemPopupPreview() {
    DynamicTheme() {
        CreateBoxPopup(
            onDismissRequest = {},
            onButtonPositiveClick = {},
            onButtonNegativeClick = {},
            boxName = "t4",
            onBoxNameChange = {},
            description = "",
            onDescriptionChange = {},
        true
        )
    }
}