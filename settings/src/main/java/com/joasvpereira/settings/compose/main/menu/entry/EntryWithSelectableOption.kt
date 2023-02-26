package com.joasvpereira.settings.compose.main.menu.entry

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.theme.DynamicTheme

@Composable
fun EntryWithSelectableOption(
    modifier: Modifier = Modifier,
    text: String,
    description: String? = null,
    listOfOptions: List<String>,
    selectedOption: Int = 0,
    onOptionChanged: (Int) -> Unit,
) {
    var isOptionsOpen by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
            .clickable {
                isOptionsOpen = !isOptionsOpen
            },
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .heightIn(min = 50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(2f),
            )
            SimpleSpace(size = 5.dp)

            val currentLocalDensity = LocalDensity.current
            var dropMenuWidth by remember {
                mutableStateOf(0.dp)
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .width(IntrinsicSize.Min)
                    .clickable { isOptionsOpen = !isOptionsOpen }
                    .onGloballyPositioned { coordinates ->
                        with(currentLocalDensity) {
                            dropMenuWidth = coordinates.size.width.toDp()
                        }
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(
                    modifier = Modifier
                        .width(1.5.dp)
                        .fillMaxHeight(.85f)
                        .background(color = MaterialTheme.colorScheme.outline, shape = RoundedCornerShape(100)),
                )
                SimpleSpace(size = 5.dp)
                Text(
                    text = if (listOfOptions.isEmpty()) "" else listOfOptions[selectedOption],
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .width(IntrinsicSize.Min)
                        .weight(2f),
                )
                SimpleSpace(size = 3.dp)
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.weight(1f),
                )
                if (listOfOptions.isNotEmpty()) {
                    DropdownMenu(
                        modifier = Modifier.widthIn(min = dropMenuWidth),
                        expanded = isOptionsOpen,
                        onDismissRequest = { isOptionsOpen = false },
                    ) {
                        listOfOptions.forEachIndexed { index, it ->
                            Text(
                                text = it,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .height(48.dp)
                                    .fillMaxWidth()
                                    .padding(end = 5.dp)
                                    .clickable {
                                        isOptionsOpen = false
                                        onOptionChanged(index)
                                    },
                            )
                        }
                    }
                }
            }
        }

        description?.let {
            Text(text = it, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@UiModePreview
@Composable
private fun EntryWithSelectebleOptionPreview() {
    DynamicTheme {
        var seleced by remember {
            mutableStateOf(0)
        }
        val list = listOf("A", "DArk", "other", "XPTO", "A Long option this is!", "see")
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                EntryWithSelectableOption(
                    modifier = Modifier.height(IntrinsicSize.Max),
                    text = "Click me to do something",
                    listOfOptions = list,
                    selectedOption = seleced,
                    description = "asdasd\nasdas\nsadas\nasda",
                    onOptionChanged = {
                        seleced = it
                    },
                )
            }
        }
    }
}