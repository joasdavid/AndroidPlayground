package com.joasvpereira.main.compose.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.joasvpereira.loggger.extentions.logThis
import pt.joasvpereira.coreui.dialog.AlertDialogWithSingleButton
import pt.joasvpereira.coreui.theme.DynamicTheme

class ItemNotFoundPopupStateHolder(
    isVisible: Boolean = false,
    var onButtonPositiveClick: () -> Unit = {},
) {
    var isVisible by mutableStateOf(isVisible)

    fun performPositiveClick() {
        "performPositiveClick".logThis(tag = "DetailsScreen")
        onButtonPositiveClick()
        isVisible = false
    }
}

@Composable
fun ItemNotFoundPopup(
    state: ItemNotFoundPopupStateHolder = remember {
        ItemNotFoundPopupStateHolder()
    },
) {
    AlertDialogWithSingleButton(
        onDismissRequest = { state.isVisible = false },
        buttonText = "Go Back",
        onButtonClick = { state.performPositiveClick() },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
        indicatorIcon = Icons.Default.Close,
        indicatorColor = MaterialTheme.colorScheme.error,
        buttonColor = MaterialTheme.colorScheme.error,
        content = {
            Box(
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = AnnotatedString(
                        text = "Ops,\n",
                        spanStyle = SpanStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                    ).plus(
                        AnnotatedString(
                            text = " no Item was found . . . .\n\nIs possible that the item was deleted.",
                        ),
                    ),
                    textAlign = TextAlign.Center,
                )
            }
        },
    )
}

@Preview
@Composable
private fun ItemNotFoundPopupPreview() {
    DynamicTheme {
        ItemNotFoundPopup()
    }
}
