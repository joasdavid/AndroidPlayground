package com.joasvpereira.main.compose.division.popup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.joasvpereira.lib.compose.spacer.SimpleSpace
import pt.joasvpereira.coreui.dialog.AlertDialogWithSingleButton
import pt.joasvpereira.coreui.preview.ThemesProvider
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.theme.ThemeOption
import pt.joasvpereira.main.R

@Composable
fun FilterPopup(
    themeOption: ThemeOption,
    onDismissRequest: () -> Unit,
    onButtonClick: () -> Unit,
    filterOptionsOption: FilterOptions,
    onFilterOptionChange: (FilterOptions) -> Unit,
) {
    DynamicTheme(themeOption) {
        AlertDialogWithSingleButton(
            onDismissRequest = onDismissRequest,
            indicatorIcon = Icons.Default.List,
            buttonText = stringResource(id = R.string.general_cancel),
            onButtonClick = onButtonClick,
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .height(48.dp)
                        .clickable {
                            onFilterOptionChange(FilterOptions.All)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    RadioButton(selected = filterOptionsOption.isAllOption(), onClick = null)
                    SimpleSpace(size = 10.dp)
                    Text(text = stringResource(R.string.filter_show_all))
                }

                Row(
                    modifier = Modifier
                        .height(48.dp)
                        .clickable {
                            onFilterOptionChange(FilterOptions.OnlyBox)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RadioButton(selected = filterOptionsOption.isOnlyBoxOption(), onClick = null)
                    SimpleSpace(size = 10.dp)
                    Text(text = stringResource(R.string.filter_show_only_boxes))
                }

                Row(
                    modifier = Modifier
                        .height(48.dp)
                        .clickable {
                            onFilterOptionChange(FilterOptions.OnlyItem)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RadioButton(selected = filterOptionsOption.isOnlyItemOption(), onClick = null)
                    SimpleSpace(size = 10.dp)
                    Text(text = stringResource(R.string.filter_show_only_items))
                }

                SimpleSpace(size = 20.dp)
            }
        }
    }
}

sealed interface FilterOptions {
    object All : FilterOptions
    object OnlyBox : FilterOptions
    object OnlyItem : FilterOptions
}

fun FilterOptions.isAllOption() = this is FilterOptions.All
fun FilterOptions.isOnlyBoxOption() = this is FilterOptions.OnlyBox
fun FilterOptions.isOnlyItemOption() = this is FilterOptions.OnlyItem

@Preview(group = "Single")
@Composable
private fun FilterPopupPreview_single() {
    FilterPopup(themeOption = ThemeOption.THEME_DEFAULT, onDismissRequest = {}, onButtonClick = {}, filterOptionsOption = FilterOptions.All, onFilterOptionChange = {})
}

@UiModePreview
@Composable
private fun FilterPopupPreview(@PreviewParameter(ThemesProvider::class) theme: ThemeOption) {
    FilterPopup(themeOption = theme, onDismissRequest = {}, onButtonClick = {}, filterOptionsOption = FilterOptions.All, onFilterOptionChange = {})
}
