package pt.joasvpereira.coreui.text.field

import android.view.KeyEvent.ACTION_DOWN
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import pt.joasvpereira.coreui.theme.DynamicTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, capitalization = KeyboardCapitalization.Sentences),
    keyboardActions: KeyboardActions? = null,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = MaterialTheme.shapes.small,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        textColor = MaterialTheme.colorScheme.primary
    )
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val keyboardActionsToUse : KeyboardActions = keyboardActions ?: buildKeyboardActions(focusManager, keyboardController)
    OutlinedTextField(
        value = value.replace("\n",""),
        onValueChange = onValueChange,
        modifier = modifier.onPreviewKeyEvent {
            if (it.key == Key.Tab && it.nativeKeyEvent.action == ACTION_DOWN){
                focusManager.moveFocus(FocusDirection.Down)
                true
            } else {
                false
            }
        },
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        label = null,
        placeholder = {
            Text(text = placeholder)
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActionsToUse,
        singleLine = singleLine,
        maxLines = maxLines,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
    )
}

@OptIn(ExperimentalComposeUiApi::class)
fun buildKeyboardActions(focusManager: FocusManager, keyboardController: SoftwareKeyboardController?) = KeyboardActions(
    onDone = {
        focusManager.clearFocus(true)
        keyboardController?.hide()
    },
    onGo = {
        focusManager.clearFocus(true)
        keyboardController?.hide()
    },
    onNext = {
             focusManager.moveFocus(FocusDirection.Next)
    },
    onPrevious = {
        focusManager.moveFocus(FocusDirection.Previous)
    },
    onSearch = {
        focusManager.clearFocus(true)
        keyboardController?.hide()
    },
    onSend = {
        focusManager.clearFocus(true)
        keyboardController?.hide()
    }
)

@Preview
@Composable
fun AppTextFieldPreview() {
    DynamicTheme {
        AppTextField(value = "dsf", onValueChange = {}, placeholder = "text")
    }
}