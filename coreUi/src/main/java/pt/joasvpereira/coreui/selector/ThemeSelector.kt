package pt.joasvpereira.coreui.selector

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.joasvpereira.lib.compose.DropdownSelector
import com.joasvpereira.lib.compose.spacer.SimpleSpace
import pt.joasvpereira.coreui.indicator.ThemeColorsIndicator
import pt.joasvpereira.coreui.preview.ThemesProvider
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.theme.ThemeOption
import pt.joasvpereira.coreui.theme.getAllThemesDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSelector(
    selectedOption: ThemeOption = ThemeOption.THEME_DEFAULT,
    onThemeChosen: (ThemeOption) -> Unit,
) {
    val list = getAllThemesDetails()
    var selection = selectedOption
    var expandable by remember { mutableStateOf(false) }
    DropdownSelector(
        modifier = Modifier
            .heightIn(min = TextFieldDefaults.MinHeight)
            .widthIn(min = TextFieldDefaults.MinWidth)
            .fillMaxWidth(),
        selectionOpenState = expandable,
        onSelectionOpenStateChanges = { expandable = !expandable },
        elements = list.toList(),
        selectedElement = list.firstOrNull { it.first == selection },
        previewContent = { it, _ ->
            it?.let {
                Row {
                    ThemeColorsIndicator(size = 24.dp, themeOption = it.first)
                    SimpleSpace(size = 5.dp)
                    Text(text = it.second)
                }
            }
        },
        onSelectedElementChanges = {
            selection = it.first
            onThemeChosen(it.first)
        },
    ) {
        Row(
            Modifier
                .heightIn(min = 48.dp)
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ThemeColorsIndicator(size = 24.dp, themeOption = it.first)
            SimpleSpace(size = 5.dp)
            Text(text = it.second)
        }
    }
}

@Preview(group = "Single")
@Composable
private fun ThemeSelectorPreview() {
    DynamicTheme() {
        Surface(modifier = Modifier.padding(10.dp)) {
            ThemeSelector(ThemeOption.values()[0]) {}
        }
    }
}

@UiModePreview
@Composable
private fun ThemeSelectorPreview(@PreviewParameter(ThemesProvider::class) theme: ThemeOption) {
    DynamicTheme(theme) {
        Surface(modifier = Modifier.padding(10.dp)) {
            ThemeSelector(theme) {}
        }
    }
}
