package com.joasvpereira.main.compose.common.popup

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joasvpereira.lib.compose.spacer.SimpleSpace
import pt.joasvpereira.coreui.dialog.DialogWithTwoButton
import pt.joasvpereira.coreui.text.field.AppTextField
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.main.R

@Composable
fun CreateBoxPopup(
    onDismissRequest: () -> Unit,
    onButtonPositiveClick: () -> Unit,
    onButtonNegativeClick: () -> Unit,
    boxName: String,
    onBoxNameChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    isOnEditMode: Boolean,
) {
    DialogWithTwoButton(
        onDismissRequest = onDismissRequest,
        indicatorIcon = painterResource(id = R.drawable.ic_box_3),
        indicatorColor = MaterialTheme.colorScheme.tertiaryContainer,
        buttonPositiveText = stringResource(id = R.string.general_save).uppercase(),
        buttonPositiveColor = MaterialTheme.colorScheme.tertiary,
        isButtonPositiveEnabled = boxName.isNotBlank(),
        onButtonPositiveClick = onButtonPositiveClick,
        buttonNegativeText = stringResource(id = R.string.general_cancel).uppercase(),
        buttonNegativeColor = MaterialTheme.colorScheme.outline,
        onButtonNegativeClick = onButtonNegativeClick,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (isOnEditMode) {
                Text(text = stringResource(R.string.update_box).uppercase(), textAlign = TextAlign.Center, style = MaterialTheme.typography.titleSmall)
            } else {
                Text(text = stringResource(R.string.create_new_box).uppercase(), textAlign = TextAlign.Center, style = MaterialTheme.typography.titleSmall)
            }
            SimpleSpace(size = 20.dp)
            AppTextField(value = boxName, onValueChange = onBoxNameChange, placeholder = stringResource(R.string.box_name_hint))
            SimpleSpace(size = 20.dp)
            AppTextField(value = description, onValueChange = onDescriptionChange, placeholder = stringResource(R.string.box_description_hint))
            SimpleSpace(size = 20.dp)
        }
    }
}

@Preview
@Composable
private fun CreateItemPopupPreview() {
    DynamicTheme {
        CreateBoxPopup(
            onDismissRequest = {},
            onButtonPositiveClick = {},
            onButtonNegativeClick = {},
            boxName = "t4",
            onBoxNameChange = {},
            description = "",
            onDescriptionChange = {},
            true,
        )
    }
}
