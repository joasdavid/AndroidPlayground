package com.joasvpereira.main.compose.create

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.ThemeColorsIndicator
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.ThemeOption
import pt.joasvpereira.coreui.getAllThemesDetails
import pt.joasvpereira.xorganizer.presentation.compose.DropdownSelector

@OptIn(ExperimentalMaterial3Api::class)
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

@Preview
@Composable
fun ThemeSelectorPreview() {
    DynamicTheme() {
        ThemeSelector(onThemeChosen = {})
    }
}