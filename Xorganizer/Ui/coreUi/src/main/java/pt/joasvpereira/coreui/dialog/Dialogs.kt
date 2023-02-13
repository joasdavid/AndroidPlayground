package pt.joasvpereira.coreui.dialog

import android.graphics.Bitmap
import android.graphics.drawable.Icon
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import pt.joasvpereira.coreui.DynamicTheme
import kotlin.math.max
import pt.joasvpereira.coreui.shield.NameShield

@Composable
internal fun ColumnScope.AlertDialogBaselineLayout(
    title: @Composable (() -> Unit)?,
    text: @Composable (() -> Unit)?
) {
    Layout(
        {
            title?.let { title ->
                Box(
                    TitlePadding
                        .layoutId("title")
                        .align(Alignment.Start)
                ) {
                    title()
                }
            }
            text?.let { text ->
                Box(
                    TextPadding
                        .layoutId("text")
                        .align(Alignment.Start)
                ) {
                    text()
                }
            }
        },
        Modifier.weight(1f, false)
    ) { measurables, constraints ->
        // Measure with loose constraints for height as we don't want the text to take up more
        // space than it needs
        val titlePlaceable = measurables.firstOrNull { it.layoutId == "title" }?.measure(
            constraints.copy(minHeight = 0)
        )
        val textPlaceable = measurables.firstOrNull { it.layoutId == "text" }?.measure(
            constraints.copy(minHeight = 0)
        )

        val layoutWidth = max(titlePlaceable?.width ?: 0, textPlaceable?.width ?: 0)

        val firstTitleBaseline = titlePlaceable?.get(FirstBaseline)?.let { baseline ->
            if (baseline == AlignmentLine.Unspecified) null else baseline
        } ?: 0
        val lastTitleBaseline = titlePlaceable?.get(LastBaseline)?.let { baseline ->
            if (baseline == AlignmentLine.Unspecified) null else baseline
        } ?: 0

        val titleOffset = TitleBaselineDistanceFromTop.roundToPx()

        // Place the title so that its first baseline is titleOffset from the top
        val titlePositionY = titleOffset - firstTitleBaseline

        val firstTextBaseline = textPlaceable?.get(FirstBaseline)?.let { baseline ->
            if (baseline == AlignmentLine.Unspecified) null else baseline
        } ?: 0

        val textOffset = if (titlePlaceable == null) {
            TextBaselineDistanceFromTop.roundToPx()
        } else {
            TextBaselineDistanceFromTitle.roundToPx()
        }

        // Combined height of title and spacing above
        val titleHeightWithSpacing = titlePlaceable?.let { it.height + titlePositionY } ?: 0

        // Align the bottom baseline of the text with the bottom baseline of the title, and then
        // add the offset
        val textPositionY = if (titlePlaceable == null) {
            // If there is no title, just place the text offset from the top of the dialog
            textOffset - firstTextBaseline
        } else {
            if (lastTitleBaseline == 0) {
                // If `title` has no baseline, just place the text's baseline textOffset from the
                // bottom of the title
                titleHeightWithSpacing - firstTextBaseline + textOffset
            } else {
                // Otherwise place the text's baseline textOffset from the title's last baseline
                (titlePositionY + lastTitleBaseline) - firstTextBaseline + textOffset
            }
        }

        // Combined height of text and spacing above
        val textHeightWithSpacing = textPlaceable?.let {
            if (lastTitleBaseline == 0) {
                textPlaceable.height + textOffset - firstTextBaseline
            } else {
                textPlaceable.height + textOffset - firstTextBaseline -
                        ((titlePlaceable?.height ?: 0) - lastTitleBaseline)
            }
        } ?: 0

        val layoutHeight = titleHeightWithSpacing + textHeightWithSpacing

        layout(layoutWidth, layoutHeight) {
            titlePlaceable?.place(0, titlePositionY)
            textPlaceable?.place(0, textPositionY)
        }
    }
}

//@Preview
@Composable
private fun AlertDialogBaselineLayoutPreview() {
    Column {
        AlertDialogBaselineLayout(title = {
            Text(text = "This is a title")
        }, text = {
            Text(text = "This is the body")
        })
    }
}

private val TitlePadding = Modifier.padding(start = 24.dp, end = 24.dp)
private val TextPadding = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 28.dp)

// Baseline distance from the first line of the title to the top of the dialog
private val TitleBaselineDistanceFromTop = 40.sp

// Baseline distance from the first line of the text to the last line of the title
private val TextBaselineDistanceFromTitle = 36.sp

// For dialogs with no title, baseline distance from the first line of the text to the top of the
// dialog
private val TextBaselineDistanceFromTop = 38.sp

@Composable
fun BaseDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Dialog(onDismissRequest = onDismissRequest, properties = properties) {
            content()
        }
    }
}

@Composable
fun SimpleDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    confirmationText: String,
    onConfirmationClick: () -> Unit,
    content: @Composable () -> Unit
) {
    BaseDialog(onDismissRequest = onDismissRequest, properties = properties) {
        Surface {
            Column(modifier = Modifier.padding(8.dp)) {
                content()
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = confirmationText,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .clickable { onConfirmationClick() }
                            .padding(vertical = 4.dp)
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun AlertDialogWithSingleButton(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    surfaceColor: Color = MaterialTheme.colorScheme.surface,
    indicatorIcon: ImageVector = Icons.Default.Warning,
    indicatorColor: Color = MaterialTheme.colorScheme.primary,
    buttonColor: Color = MaterialTheme.colorScheme.primary,
    buttonText: String,
    onButtonClick: () -> Unit,
    content: @Composable () -> Unit
) {
    AlertDialogWithSingleButton(
        onDismissRequest = onDismissRequest,
        properties = properties,
        surfaceColor = surfaceColor,
        indicatorIcon = rememberVectorPainter(indicatorIcon),
        indicatorColor = indicatorColor,
        buttonColor = buttonColor,
        buttonText = buttonText,
        onButtonClick = onButtonClick,
        content = content,
    )
}

@Composable
fun AlertDialogWithSingleButton(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    surfaceColor: Color = MaterialTheme.colorScheme.surface,
    indicatorIcon: ImageBitmap,
    indicatorColor: Color = MaterialTheme.colorScheme.primary,
    buttonColor: Color = MaterialTheme.colorScheme.primary,
    buttonText: String,
    onButtonClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val painter = remember(indicatorIcon) { BitmapPainter(indicatorIcon) }
    AlertDialogWithSingleButton(
        onDismissRequest = onDismissRequest,
        properties = properties,
        surfaceColor = surfaceColor,
        indicatorIcon = painter,
        indicatorColor = indicatorColor,
        buttonColor = buttonColor,
        buttonText = buttonText,
        onButtonClick = onButtonClick,
        content = content,
    )
}

@Composable
fun AlertDialogWithSingleButton(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    surfaceColor: Color = MaterialTheme.colorScheme.surface,
    indicatorIcon: Painter,
    indicatorColor: Color = MaterialTheme.colorScheme.primary,
    buttonColor: Color = MaterialTheme.colorScheme.primary,
    buttonText: String,
    onButtonClick: () -> Unit,
    content: @Composable () -> Unit
) {
    BasedStyledDialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
        surfaceColor = surfaceColor,
        indicatorIcon = indicatorIcon,
        indicatorColor = indicatorColor,
        buttonAreaContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = buttonColor)
                    .clickable { onButtonClick() }, contentAlignment = Alignment.Center
            ) {
                Text(text = buttonText, fontWeight = FontWeight.Bold, color = contentColorFor(buttonColor))
            }
        },
        content = content,
    )
}

@Composable
fun BasedStyledDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    surfaceColor: Color = MaterialTheme.colorScheme.surface,
    indicatorIcon: Painter,
    indicatorColor: Color = MaterialTheme.colorScheme.primary,
    buttonAreaContent: @Composable RowScope.() -> Unit,
    content: @Composable () -> Unit,
) {
    BaseDialog(onDismissRequest = onDismissRequest, properties = properties) {
        Box(modifier = Modifier
            .clip(shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
        ) {
            ConstraintLayout(
                modifier = Modifier
            ) {
                val (
                    contentBoxRef,
                    iconRef,
                    buttonRef,
                ) = createRefs()

                Surface(
                    modifier = Modifier
                        .constrainAs(contentBoxRef) {
                            top.linkTo(iconRef.bottom, margin = -25.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.wrapContent
                        },
                    color = surfaceColor
                ) {
                    Box(
                        modifier = Modifier
                            .widthIn(min = 150.dp)
                            .width(IntrinsicSize.Min)
                            .padding(horizontal = 16.dp)
                            .padding(top = 40.dp)
                            .padding(bottom = 15.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        content()
                    }
                }

                Row(
                    modifier = Modifier
                        .height(48.dp)
                        .background(surfaceColor)
                        .constrainAs(buttonRef) {
                            top.linkTo(contentBoxRef.bottom)
                            start.linkTo(contentBoxRef.start)
                            end.linkTo(contentBoxRef.end)
                            width = Dimension.fillToConstraints
                        },
                ) {
                    buttonAreaContent()
                }

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(color = indicatorColor, CircleShape)
                        .padding(5.dp)
                        .constrainAs(iconRef) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(painter = indicatorIcon, contentDescription = null, tint = contentColorFor(indicatorColor))
                }
            }
        }
    }
}

@Composable
fun DialogWithTwoButton(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    surfaceColor: Color = MaterialTheme.colorScheme.surface,
    indicatorIcon: Painter,
    indicatorColor: Color = MaterialTheme.colorScheme.primary,
    buttonPositiveColor: Color = MaterialTheme.colorScheme.primary,
    buttonPositiveText: String,
    onButtonPositiveClick: () -> Unit,
    isButtonPositiveEnabled: Boolean = true,
    buttonNegativeColor: Color = MaterialTheme.colorScheme.secondary,
    buttonNegativeText: String,
    onButtonNegativeClick: () -> Unit,
    isButtonNegativeEnabled: Boolean = true,
    content: @Composable () -> Unit
) {
    BasedStyledDialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
        surfaceColor = surfaceColor,
        indicatorIcon = indicatorIcon,
        indicatorColor = indicatorColor,
        buttonAreaContent = {
            val alphaPositive = if (isButtonPositiveEnabled) 1f else .25f
            val alphaNegative = if (isButtonNegativeEnabled) 1f else .25f
            Box(modifier = Modifier
                .background(buttonPositiveColor.copy(alphaPositive))
                .fillMaxSize()
                .weight(1f)
                .then(if (isButtonPositiveEnabled) Modifier.clickable { onButtonPositiveClick() } else Modifier),
                contentAlignment = Alignment.Center
            ) {
                Text(text = buttonPositiveText, fontWeight = FontWeight.Bold, color = contentColorFor(backgroundColor = buttonPositiveColor).copy(alphaPositive))
            }
            Box(
                modifier = Modifier
                    .background(buttonNegativeColor.copy(alphaNegative))
                    .fillMaxSize()
                    .weight(1f)
                    .clickable { onButtonNegativeClick() }, contentAlignment = Alignment.Center
            ) {
                Text(text = buttonNegativeText, fontWeight = FontWeight.Bold, color = contentColorFor(backgroundColor = buttonNegativeColor).copy(alphaNegative))
            }
        },
        content = content,
    )
}

@Composable
fun DialogWithTwoButton(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    surfaceColor: Color = MaterialTheme.colorScheme.surface,
    indicatorIcon: ImageVector,
    indicatorColor: Color = MaterialTheme.colorScheme.primary,
    buttonPositiveColor: Color = MaterialTheme.colorScheme.primary,
    buttonPositiveText: String,
    onButtonPositiveClick: () -> Unit,
    isButtonPositiveEnabled: Boolean = true,
    buttonNegativeColor: Color = MaterialTheme.colorScheme.secondary,
    buttonNegativeText: String,
    onButtonNegativeClick: () -> Unit,
    isButtonNegativeEnabled: Boolean = true,
    content: @Composable () -> Unit
) {
    DialogWithTwoButton(
        onDismissRequest = onDismissRequest,
        properties = properties,
        surfaceColor = surfaceColor,
        indicatorIcon = rememberVectorPainter(image = indicatorIcon),
        indicatorColor = indicatorColor,
        buttonPositiveColor = buttonPositiveColor,
        onButtonPositiveClick = onButtonPositiveClick,
        buttonPositiveText = buttonPositiveText,
        buttonNegativeColor = buttonNegativeColor,
        buttonNegativeText = buttonNegativeText,
        onButtonNegativeClick = onButtonNegativeClick,
        content = content,
        isButtonPositiveEnabled = isButtonPositiveEnabled,
        isButtonNegativeEnabled = isButtonNegativeEnabled,
    )
}

@Composable
fun DialogWithTwoButton(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    surfaceColor: Color = MaterialTheme.colorScheme.surface,
    indicatorIcon: ImageBitmap,
    indicatorColor: Color = MaterialTheme.colorScheme.primary,
    buttonPositiveColor: Color = MaterialTheme.colorScheme.primary,
    buttonPositiveText: String,
    onButtonPositiveClick: () -> Unit,
    isButtonPositiveEnabled: Boolean = true,
    buttonNegativeColor: Color = MaterialTheme.colorScheme.secondary,
    buttonNegativeText: String,
    onButtonNegativeClick: () -> Unit,
    isButtonNegativeEnabled: Boolean = true,
    content: @Composable () -> Unit
) {
    val painter = remember(indicatorIcon) { BitmapPainter(indicatorIcon) }
    DialogWithTwoButton(
        onDismissRequest = onDismissRequest,
        properties = properties,
        surfaceColor = surfaceColor,
        indicatorIcon = painter,
        indicatorColor = indicatorColor,
        buttonPositiveColor = buttonPositiveColor,
        onButtonPositiveClick = onButtonPositiveClick,
        buttonPositiveText = buttonPositiveText,
        buttonNegativeColor = buttonNegativeColor,
        buttonNegativeText = buttonNegativeText,
        onButtonNegativeClick = onButtonNegativeClick,
        content = content,
        isButtonPositiveEnabled = isButtonPositiveEnabled,
        isButtonNegativeEnabled = isButtonNegativeEnabled,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun DialogWithTwoButtonPreview() {
    DynamicTheme {
        var test by remember {
            mutableStateOf("")
        }
        Box(modifier = Modifier.fillMaxSize()) {
            DialogWithTwoButton(
                onDismissRequest = { /*TODO*/ },
                indicatorIcon = Icons.Default.Delete,
                indicatorColor = MaterialTheme.colorScheme.error,
                buttonPositiveText = "DELETE",
                buttonPositiveColor = MaterialTheme.colorScheme.error,
                isButtonPositiveEnabled = !test.isBlank(),
                onButtonPositiveClick = { /*TODO*/ },
                buttonNegativeText = "CANCEL",
                buttonNegativeColor = MaterialTheme.colorScheme.error,
                onButtonNegativeClick = { /*TODO*/ }) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "To delete division write it's name in all caps \n \"LIVING ROOM`", textAlign = TextAlign.Center)
                    SimpleSpace(size = 20.dp)
                    OutlinedTextField(value = test, onValueChange = { test = it })
                    SimpleSpace(size = 20.dp)
                }
            }
        }
    }
}


@Preview
@Composable
private fun AlertDialogPreview() {
    DynamicTheme {
        val contxt = LocalContext.current
        AlertDialogWithSingleButton(indicatorIcon = Icons.Default.Close, onDismissRequest = {}, buttonText = "Close", onButtonClick = {
            Toast.makeText(contxt, "click!!!", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Hello")
        }
    }
}
/*
@Preview
@Composable
private fun SimpleDialogPreview() {
    SimpleDialog(onDismissRequest = {}, confirmationText = "OK", onConfirmationClick = {}, content = {
        Text(text = "This is a long test")
    })
}

@Preview
@Composable
private fun BaseDialogPreview() {
    BaseDialog(onDismissRequest = {}) {
        Text(text = "Dialog with text only")
    }
}*/
