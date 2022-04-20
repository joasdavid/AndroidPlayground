package com.joasvpereira.dev.mokeupui.compose.screen.organizer.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import compose.icons.AllIcons
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.HomeSolid
import pt.joasvpereira.xorganizer.ui.theme.DynamicTheme
import pt.joasvpereira.xorganizer.ui.theme.ThemeOption

@Composable
fun OnSurfaceOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current.copy(color = Color.White),
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = MaterialTheme.shapes.small,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = MaterialTheme.colorScheme.onSurface,
        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = .5f),
        placeholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = .5f),
        focusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = .5f),
        unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = .5f)
    )
) {
    Surface {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            label = label,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            interactionSource = interactionSource,
            shape = shape,
            colors = colors
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IconSelector(
    backgroundColor: Color = MaterialTheme.colorScheme.secondary.copy(alpha = .7f),
    iconTintColor: Color = MaterialTheme.colorScheme.onSecondary
) {
    var expanded by remember { mutableStateOf(false) }
    var iconSelected by remember { mutableStateOf(LineAwesomeIcons.HomeSolid) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(
                    color = backgroundColor,
                    shape = CircleShape
                )
                .clickable {
                    expanded = true
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = iconSelected,
                contentDescription = "",
                tint = iconTintColor,
                modifier = Modifier.size(48.dp)
            )
            // region DropdownMenu
            /*DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                LineAwesomeIcons.AllIcons.take(100).forEach {
                    DropdownMenuItem(onClick = {
                        expanded = false
                        iconSelected = it
                    }) {
                        Icon(
                            imageVector = it,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier
                        )
                        Spacer(modifier = Modifier.size(5.dp))
                        Text(text = it.name)
                    }
                }
            }*/
            //endregion

            if (expanded) {
                AlertDialog(
                    onDismissRequest = {
                        expanded = false
                    },
                    title = {
                        Text(text = "Choose your image.")
                    },
                    text = {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(fraction = .8f)
                        ) {
                            LazyVerticalGrid(
                                cells = GridCells.Fixed(3),
                                contentPadding = PaddingValues(
                                    start = 0.dp,
                                    top = 16.dp,
                                    end = 0.dp,
                                    bottom = 16.dp
                                )
                            ) {
                                items(LineAwesomeIcons.AllIcons.size) { index ->
                                    val currentItem = LineAwesomeIcons.AllIcons[index]
                                    Icon(
                                        imageVector = currentItem,
                                        contentDescription = currentItem.name,
                                        tint = Color.White,
                                        modifier = Modifier
                                            .size(48.dp)
                                            .clickable {
                                                iconSelected = currentItem
                                                expanded = false
                                            }
                                    )
                                }
                            }
                        }
                    },
                    confirmButton = {
                        Row(
                            modifier = Modifier.padding(all = 8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = { expanded = false }
                            ) {
                                Text("Dismiss")
                            }
                        }
                    }
                )
            }
        }
        //region Add list
        /*if(expanded) {
            Box(Modifier.height((48*3).dp).background(color = Color.Black.copy(alpha = .2f))) {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(3),
                    contentPadding = PaddingValues(
                        start = 0.dp,
                        top = 16.dp,
                        end = 0.dp,
                        bottom = 16.dp
                    )
                ){
                    items(LineAwesomeIcons.AllIcons.size) { index ->
                        val currentItem = LineAwesomeIcons.AllIcons[index]
                        Icon(
                            imageVector = currentItem,
                            contentDescription = currentItem.name,
                            tint = Color.White,
                            modifier = Modifier.size(48.dp).clickable {
                                iconSelected = currentItem
                                expanded = false
                            }
                        )
                    }
                }
            }
        }*/
        //endregion
    }
}


@Composable
fun ThemeColorsIndicator(
    themeOption: ThemeOption = ThemeOption.THEME_BLUE,
    size: Dp = 48.dp,
    borderStroke: BorderStroke = BorderStroke(width = 1.5.dp, color = Color.White)
) {
    DynamicTheme(themeOption) {
        Box {
            Row(
                modifier = Modifier
                    .size(size)
                    .border(
                        border = borderStroke,
                        shape = CircleShape
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .fillMaxHeight()
                        .weight(1f)
                        .background(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = semiCircleRightShape
                        )
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .fillMaxHeight()
                        .weight(1f)
                        .background(
                            color = MaterialTheme.colorScheme.tertiaryContainer,
                            shape = semiCircleLeftShape
                        )
                )
            }
            Row(
                modifier = Modifier
                    .size(size)
                    .border(
                        border = borderStroke,
                        shape = CircleShape
                    )
                    .rotate(90f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .fillMaxHeight()
                        .weight(1f)
                        .background(
                            color = Color.Transparent,
                            shape = semiCircleRightShape
                        )
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .fillMaxHeight()
                        .weight(1f)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = semiCircleLeftShape
                        )
                )
            }
        }
    }
}

@Preview
@Composable
fun ThemeColorsIndicatorPreview() {
    DynamicTheme(ThemeOption.THEME_BLUE) {
        ThemeColorsIndicator()
    }
}

val semiCircleRightShape = GenericShape { size, _ ->
    arcTo(
        rect = Rect(center = Offset(x = size.width, y = size.height / 2), size.width),
        startAngleDegrees = 90f,
        sweepAngleDegrees = 180f,
        false
    )
}

val semiCircleLeftShape = GenericShape { size, _ ->
    arcTo(
        rect = Rect(center = Offset(x = 0f, y = size.height / 2), size.width),
        startAngleDegrees = -90f,
        sweepAngleDegrees = 180f,
        false
    )
}

val semiCircleBottomShape = GenericShape { size, _ ->
    arcTo(
        rect = Rect(center = Offset(x = 0f, y = size.height / 2), size.width),
        startAngleDegrees = -180f,
        sweepAngleDegrees = 180f,
        false
    )
}

data class IconData(val painter: Painter, val contentDescription: String = "")

@Composable
fun IconAndCounter(
    color: Color = LocalContentColor.current,
    iconData: IconData,
    count: Int = 0
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = iconData.painter,
            contentDescription = iconData.contentDescription,
            tint = color,
            modifier = Modifier.size(16.dp)
        )
        Text(text = "$count", color = color)
    }
}