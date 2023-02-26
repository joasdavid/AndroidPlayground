package com.joasvpereira.main.compose.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.text.field.AppTextField
import pt.joasvpereira.coreui.theme.DynamicTheme

@Composable
fun ColumnScope.DivisionNameAndDescription(
    name: String,
    onNameChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
) {
    AppTextField(
        modifier = Modifier.fillMaxWidth(),
        value = name,
        onValueChange = onNameChange,
        placeholder = "Division name",
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    )
    Spacer(modifier = Modifier.size(20.dp))
    AppTextField(
        modifier = Modifier.fillMaxWidth(),
        value = description,
        onValueChange = onDescriptionChange,
        placeholder = "Division description",
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    )
}

@UiModePreview
@Composable
private fun DivisionNameAndDescriptionPreview(@PreviewParameter(DivisionNameAndDescriptionPreviewProvider::class) data: NameDescriptionData) {
    DynamicTheme {
        Column {
            DivisionNameAndDescription(
                name = data.name,
                onNameChange = {},
                description = data.description,
                onDescriptionChange = {},
            )
        }
    }
}

data class NameDescriptionData(val name: String, val description: String)

class DivisionNameAndDescriptionPreviewProvider : PreviewParameterProvider<NameDescriptionData> {
    override val values = sequenceOf(
        NameDescriptionData(name = "", description = ""),
        NameDescriptionData(name = "Test name", description = ""),
        NameDescriptionData(name = "", description = "Test description"),
        NameDescriptionData(name = "Test name", description = "Test description"),
    )
}

object PreviewData
