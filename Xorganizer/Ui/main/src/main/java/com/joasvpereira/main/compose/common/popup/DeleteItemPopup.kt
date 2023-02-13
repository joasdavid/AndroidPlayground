package com.joasvpereira.main.compose.common.popup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import com.joasvpereira.loggger.extentions.logThis
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.dialog.DialogWithTwoButton
import pt.joasvpereira.coreui.text.field.AppTextField

class DeleteItemPopupStateHolder(
    confirmationName: String = "",
    isVisible: Boolean = false,
    var onButtonPositiveClick: () -> Unit = {},
    var onButtonNegativeClick: () -> Unit = {},
) {
    var name by mutableStateOf("")
    var confirmationName by mutableStateOf(confirmationName.toUpperCase(Locale.current))
    var isVisible by mutableStateOf(isVisible)
    val isEnabled: Boolean
        get() = name.equals(confirmationName, ignoreCase = false)

    fun performPositiveClick() {
        if (isEnabled) {
            "performPositiveClick".logThis(tag = "DetailsScreen")
            onButtonPositiveClick()
            isVisible = false
        }
    }

    fun performNegativeClick() {
        "performNegativeClick".logThis(tag = "DetailsScreen")
        onButtonNegativeClick()
        isVisible = false
    }
}

@Composable
fun DeleteItemPopup(
    deleteItemPopupStateHolder: DeleteItemPopupStateHolder = remember {
        DeleteItemPopupStateHolder()
    }
) {
    DialogWithTwoButton(
        onDismissRequest = { deleteItemPopupStateHolder.isVisible = false },
        indicatorIcon = Icons.Default.Delete,
        indicatorColor = MaterialTheme.colorScheme.error,
        buttonPositiveText = "DELETE",
        buttonPositiveColor = MaterialTheme.colorScheme.error,
        isButtonPositiveEnabled = deleteItemPopupStateHolder.isEnabled,
        onButtonPositiveClick = { deleteItemPopupStateHolder.performPositiveClick() },
        buttonNegativeText = "CANCEL",
        buttonNegativeColor = MaterialTheme.colorScheme.surfaceVariant,
        onButtonNegativeClick = { deleteItemPopupStateHolder.performNegativeClick() }) {

        val nameUppercase = deleteItemPopupStateHolder.confirmationName.toUpperCase(Locale.current)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "To delete division write it's name in all caps \n \"$nameUppercase", textAlign = TextAlign.Center)
            SimpleSpace(size = 20.dp)
            AppTextField(
                value = deleteItemPopupStateHolder.name,
                onValueChange = { deleteItemPopupStateHolder.name = it },
                placeholder = "",
                keyboardOptions = KeyboardOptions(autoCorrect = false, imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(onSend = { deleteItemPopupStateHolder.performPositiveClick() })
            )
            SimpleSpace(size = 20.dp)
        }
    }
}

@Preview
@Composable
private fun DeleteItemPopupPreview() {
    DynamicTheme() {
        DeleteItemPopup()
    }
}