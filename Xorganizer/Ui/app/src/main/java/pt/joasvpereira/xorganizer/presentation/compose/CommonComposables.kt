package com.joasvpereira.dev.mokeupui.compose.screen.organizer.main


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import compose.icons.AllIcons
import compose.icons.LineAwesomeIcons
import pt.joasvpereira.xorganizer.presentation.compose.DropdownSelector
import pt.joasvpereira.xorganizer.presentation.theme.DynamicTheme
import pt.joasvpereira.xorganizer.presentation.theme.ThemeOption
import pt.joasvpereira.xorganizer.presentation.theme.getAllThemesDetails

@Composable
fun IconSelector(
    iconSelected: ImageVector,
    onIconSelected: (ImageVector) -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.secondary.copy(alpha = .7f),
    iconTintColor: Color = MaterialTheme.colorScheme.onSecondary
) {
    var expanded by remember { mutableStateOf(false) }
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
                                columns = GridCells.Fixed(3),
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
                                                onIconSelected(currentItem)
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
    themeOption: ThemeOption = ThemeOption.THEME_DEFAULT,
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
            Box(
                modifier = Modifier
                    .width(borderStroke.width)
                    .height(size)
                    .background(borderStroke.brush)
                    .align(Alignment.Center)
            )
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
            Box(
                modifier = Modifier
                    .width(size)
                    .height(1.dp)
                    .background(borderStroke.brush)
                    .align(Alignment.Center)
            )

        }
    }
}

//@Preview(showBackground = true, backgroundColor = 0x989a82)
@Composable
fun ThemeColorsIndicatorPreview() {
    ThemeColorsIndicator()
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

@Composable
fun ThemeSelector(
    onThemeChosen: (ThemeOption) -> Unit
) {
    val list = getAllThemesDetails()
    var selection by remember { mutableStateOf(list.firstOrNull()?.first) }
    var expandable by remember { mutableStateOf(false) }
    DropdownSelector(
        modifier = Modifier
            .heightIn(min = TextFieldDefaults.MinHeight)
            .widthIn(min = TextFieldDefaults.MinWidth)
            .fillMaxWidth(),
        selectionOpenState = expandable,
        onSelectionOpenStateChanges = { expandable = !expandable },
        options = list.toList(),
        selectedOption = list.firstOrNull { it.first == selection },
        previewContent = { it, _ ->
            it?.let {
                Row {
                    ThemeColorsIndicator(size = 24.dp, themeOption = it.first)
                    SimpleSpace(size = 5.dp)
                    Text(text = it.second)
                }
            }
        },
        onSelectedOptionChanges = {
            selection = it.first
            onThemeChosen(it.first)
        }
    ) {
        Row(
            Modifier
                .heightIn(min = 48.dp)
                .padding(horizontal = 4.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            ThemeColorsIndicator(size = 24.dp, themeOption = it.first)
            SimpleSpace(size = 5.dp)
            Text(text = it.second)
        }
    }
}

//@Preview(showBackground = true, backgroundColor = 0x989a82)
@Composable
fun ThemeSelectorPreview() {
    ThemeSelector {}
}

//@Preview(showBackground = true, backgroundColor = 0x989a82)
@Composable
fun ThemeSelectorExpandedContentPreview() {
    Column {
        Row(
            Modifier
                .heightIn(min = 48.dp)
                .padding(horizontal = 4.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            ThemeColorsIndicator(size = 24.dp)
            SimpleSpace(size = 5.dp)
            Text(text = "this is a theme")
        }
        Row(
            Modifier
                .heightIn(min = 48.dp)
                .padding(horizontal = 4.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            ThemeColorsIndicator(size = 24.dp)
            SimpleSpace(size = 5.dp)
            Text(text = "this is a theme")
        }
    }
}

@Composable
fun SimpleSpace(size: Dp) {
    Spacer(modifier = Modifier.size(size = size))
}

data class ToolBarBackConfig(
    val backIcon: ImageVector = Icons.Default.ArrowBack,
    val onBackClick: () -> Unit
)

@Composable
fun ToolbarTitleCentered(
    title: String = "",
    toolBarBackConfig: ToolBarBackConfig? = null,
    horizontalPadding: Dp = 16.dp,
    useInsetsPaddingForStatusBars: Boolean = true
) {
    Box(
        modifier = Modifier
            .then(
                if (useInsetsPaddingForStatusBars) {
                    Modifier.windowInsetsPadding(WindowInsets.statusBars)
                } else {
                    Modifier
                }
            )
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = horizontalPadding)
    )
    {
        toolBarBackConfig?.let {
            Icon(
                imageVector = it.backIcon,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .clickable { it.onBackClick() }
                    .align(Alignment.CenterStart)
            )
        }

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )

        // TODO: this will be implemented later
        /*Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = null
        )*/
    }
}

@Preview()
@Composable
fun ToolbarTitleCenterdPreview() {
    Column {
        ToolbarTitleCentered(title = "Test Toolbar", toolBarBackConfig = null)
        SimpleSpace(size = 10.dp)
        ToolbarTitleCentered(title = "Test Toolbar with back button", toolBarBackConfig = ToolBarBackConfig(
            onBackClick = {

            }
        ))
        SimpleSpace(size = 10.dp)
        ToolbarTitleCentered(
            title = "Test Toolbar",
            toolBarBackConfig = null,
            horizontalPadding = 20.dp,
            useInsetsPaddingForStatusBars = false
        )
        SimpleSpace(size = 10.dp)
        Column(
            modifier = Modifier
        ) {
            ToolbarTitleCentered(title = "divisionName", toolBarBackConfig = ToolBarBackConfig(onBackClick = {}), horizontalPadding = 5.dp)
            /*SimpleSpace(size = 20.dp)
            DivisionChart(
                nrFolders,
                percentageFolders,
                nrItems,
                percentageItems,
                shieldImg,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )*/
        }
    }
}

