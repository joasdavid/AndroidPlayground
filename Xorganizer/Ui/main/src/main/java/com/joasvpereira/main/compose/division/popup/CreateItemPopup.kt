package com.joasvpereira.main.compose.division.popup

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
fun CreateItemPopup(
    onDismissRequest : () -> Unit,
    onButtonPositiveClick : () -> Unit,
    onButtonNegativeClick : () -> Unit,
    itemName : String,
    onItemNameChange: (String) -> Unit,
    description : String,
    onDescriptionChange: (String) -> Unit,
    isOnEditMode: Boolean
) {
    DialogWithTwoButton(
        onDismissRequest = onDismissRequest,
        indicatorIcon = painterResource(id = R.drawable.ic_item),
        indicatorColor = MaterialTheme.colorScheme.primaryContainer,
        buttonPositiveText = if (isOnEditMode) "Updade" else "Save",
        buttonPositiveColor = MaterialTheme.colorScheme.primary,
        isButtonPositiveEnabled = itemName.isNotBlank(),
        onButtonPositiveClick = onButtonPositiveClick,
        buttonNegativeText = "Close",
        buttonNegativeColor = MaterialTheme.colorScheme.outline,
        onButtonNegativeClick = onButtonNegativeClick) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (isOnEditMode) {
                Text(text = "Update item".toUpperCase(Locale.current), textAlign = TextAlign.Center, style = MaterialTheme.typography.titleSmall)
            } else {
                Text(text = "Create a new item".toUpperCase(Locale.current), textAlign = TextAlign.Center, style = MaterialTheme.typography.titleSmall)
            }
            SimpleSpace(size = 20.dp)
            AppTextField(value = itemName, onValueChange = onItemNameChange, placeholder = "Item name")
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
        CreateItemPopup(
            onDismissRequest = {},
            onButtonPositiveClick = {},
            onButtonNegativeClick = {},
            itemName = "",
            onItemNameChange = {},
            description = "",
            onDescriptionChange = {},
            isOnEditMode = false
        )
    }
}