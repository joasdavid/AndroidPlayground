package com.joasvpereira.main.compose.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.ThemeSelector
import com.joasvpereira.main.presentation.icons.DivisionIcon
import pt.joasvpereira.coreui.preview.ThemesProvider
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.scaffold.AppScaffold
import pt.joasvpereira.coreui.scaffold.ToolBarConfig
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.theme.ThemeOption

@Composable
fun CreateDivisionSection(
    isLoading: Boolean,
    onIconChange: (DivisionIcon) -> Unit,
    name: String,
    onNameChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    onThemeChange: (ThemeOption) -> Unit,
    onSave: () -> Unit,
    onCloseClick: () -> Unit,
) {
    AppScaffold(
        isLoading = isLoading,
        toolBarConfig = ToolBarConfig(
            title = "",
            onRightIconClick = { onCloseClick() },
        ),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SimpleSpace(size = 100.dp)
            DivisionIconSelector(modifier = Modifier.fillMaxWidth(.75f), defaultPos = 0, onIconSelected = onIconChange)
            Spacer(modifier = Modifier.size(20.dp))
            DivisionNameAndDescription(
                name = name,
                onNameChange = onNameChange,
                description = description,
                onDescriptionChange = onDescriptionChange,
            )
            Spacer(modifier = Modifier.size(20.dp))
            ThemeSelector {
                onThemeChange(it)
            }
            SimpleSpace(size = 45.dp)
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onSave() },
            ) {
                Text(text = "Save")
            }
        }
    }
}

@UiModePreview
@Composable
private fun CreateDivisionSectionPreview(@PreviewParameter(ThemesProvider::class) theme: ThemeOption) {
    DynamicTheme {
        CreateDivisionSection(
            isLoading = false,
            onCloseClick = {},
            onIconChange = {},
            name = "",
            onNameChange = {},
            description = "",
            onDescriptionChange = {},
            onThemeChange = {},
            onSave = {},
        )
    }
}
