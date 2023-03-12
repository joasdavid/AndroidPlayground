package com.joasvpereira.main.compose.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.joasvpereira.lib.compose.spacer.SimpleSpace
import com.joasvpereira.loggger.extentions.logThis
import com.joasvpereira.main.presentation.icons.DivisionIcon
import pt.joasvpereira.coreui.preview.ThemesProvider
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.scaffold.AppScaffold
import pt.joasvpereira.coreui.scaffold.ToolBarConfig
import pt.joasvpereira.coreui.screen.getActivityWindowHeight
import pt.joasvpereira.coreui.selector.ThemeSelector
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.theme.ThemeOption
import pt.joasvpereira.coreui.util.WindowSizeHelper
import pt.joasvpereira.main.R

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
    canDelete: Boolean = false,
    onDeleteClick: () -> Unit = {},
) {
    AppScaffold(
        isLoading = isLoading,
        toolBarConfig = ToolBarConfig(
            title = "",
            onRightIconClick = { onCloseClick() },
        ),
    ) {
        val topSpace = when (getActivityWindowHeight()) {
            WindowSizeHelper.HeightSize.Compact,
            WindowSizeHelper.HeightSize.Medium,
            -> 20.dp
            WindowSizeHelper.HeightSize.Large,
            WindowSizeHelper.HeightSize.Expanded,
            -> 100.dp
        }.logThis("ScreenSize")
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SimpleSpace(size = topSpace)
            DivisionIconSelector(
                defaultPos = 0,
                onIconSelected = onIconChange,
            )
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
                Text(text = stringResource(R.string.general_save))
            }
            if (canDelete) {
                SimpleSpace(size = 20.dp)
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onDeleteClick,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                ) {
                    Text(text = stringResource(id = R.string.general_delete))
                }
            }
        }
    }
}

@Preview(group = "Single")
@Composable
private fun CreateDivisionSectionPreview_single() {
    DynamicTheme() {
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

@UiModePreview
@Composable
private fun CreateDivisionSectionPreview(@PreviewParameter(ThemesProvider::class) theme: ThemeOption) {
    DynamicTheme(theme) {
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
