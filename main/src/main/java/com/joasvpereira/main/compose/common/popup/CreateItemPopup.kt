package com.joasvpereira.main.compose.common.popup

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joasvpereira.lib.compose.spacer.SimpleSpace
import com.joasvpereira.loggger.extentions.logThis
import pt.joasvpereira.coreui.dialog.DialogWithTwoButton
import pt.joasvpereira.coreui.text.field.AppTextField
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.main.R

@Composable
fun CreateItemPopup(
    stateHolder: CreateItemPopupStateHolder = remember {
        CreateItemPopupStateHolder(false)
    },
) {
    DialogWithTwoButton(
        onDismissRequest = { stateHolder.isVisible = false },
        indicatorIcon = painterResource(id = R.drawable.ic_item),
        indicatorColor = MaterialTheme.colorScheme.primaryContainer,
        buttonPositiveText = stringResource(id = R.string.general_save).uppercase(),
        buttonPositiveColor = MaterialTheme.colorScheme.primary,
        isButtonPositiveEnabled = stateHolder.name.isNotBlank(),
        onButtonPositiveClick = { stateHolder.performPositiveClick() },
        buttonNegativeText = stringResource(id = R.string.general_cancel).uppercase(),
        buttonNegativeColor = MaterialTheme.colorScheme.outline,
        onButtonNegativeClick = { stateHolder.performNegativeClick() },
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (stateHolder.isOnEditMode) {
                Text(
                    text = stringResource(id = R.string.update_item).uppercase(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall,
                )
            } else {
                Text(
                    text = stringResource(id = R.string.new_item).uppercase(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
            SimpleSpace(size = 20.dp)
            AppTextField(value = stateHolder.name, onValueChange = { stateHolder.name = it }, placeholder = stringResource(id = R.string.item_name_hint))
            SimpleSpace(size = 20.dp)
            AppTextField(value = stateHolder.description, onValueChange = { stateHolder.description = it }, placeholder = stringResource(id = R.string.item_description_hint))
            SimpleSpace(size = 20.dp)
        }
    }
}

class CreateItemPopupStateHolder(
    isOnEditMode: Boolean,
    isVisible: Boolean = false,
    var onButtonPositiveClick: () -> Unit = {},
    var onButtonNegativeClick: () -> Unit = {},
) {
    var name by mutableStateOf("")
    var description by mutableStateOf("")
    var isVisible by mutableStateOf(isVisible)
    var isOnEditMode: Boolean by mutableStateOf(isOnEditMode)

    fun performPositiveClick() {
        "performPositiveClick".logThis(tag = "DetailsScreen")
        onButtonPositiveClick()
        isVisible = false
    }

    fun performNegativeClick() {
        "performNegativeClick".logThis(tag = "DetailsScreen")
        onButtonNegativeClick()
        isVisible = false
    }
}

@Deprecated("Use the CreateItemPopup with state instead.")
@Composable
fun CreateItemPopup(
    onDismissRequest: () -> Unit,
    onButtonPositiveClick: () -> Unit,
    onButtonNegativeClick: () -> Unit,
    itemName: String,
    onItemNameChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    isOnEditMode: Boolean,
) {
    DialogWithTwoButton(
        onDismissRequest = onDismissRequest,
        indicatorIcon = painterResource(id = R.drawable.ic_item),
        indicatorColor = MaterialTheme.colorScheme.primaryContainer,
        buttonPositiveText = stringResource(id = R.string.general_save).uppercase(),
        buttonPositiveColor = MaterialTheme.colorScheme.primary,
        isButtonPositiveEnabled = itemName.isNotBlank(),
        onButtonPositiveClick = onButtonPositiveClick,
        buttonNegativeText = stringResource(id = R.string.general_cancel).uppercase(),
        buttonNegativeColor = MaterialTheme.colorScheme.outline,
        onButtonNegativeClick = onButtonNegativeClick,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (isOnEditMode) {
                Text(
                    text = stringResource(id = R.string.update_item).uppercase(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall,
                )
            } else {
                Text(
                    text = stringResource(id = R.string.new_item).uppercase(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
            SimpleSpace(size = 20.dp)
            AppTextField(value = itemName, onValueChange = onItemNameChange, placeholder = stringResource(R.string.item_name_hint))
            SimpleSpace(size = 20.dp)
            AppTextField(value = description, onValueChange = onDescriptionChange, placeholder = stringResource(R.string.item_description_hint))
            SimpleSpace(size = 20.dp)
        }
    }
}

@Preview
@Composable
private fun CreateItemPopupPreview() {
    DynamicTheme {
        CreateItemPopup(
            onDismissRequest = {},
            onButtonPositiveClick = {},
            onButtonNegativeClick = {},
            itemName = "",
            onItemNameChange = {},
            description = "",
            onDescriptionChange = {},
            isOnEditMode = false,
        )
    }
}
