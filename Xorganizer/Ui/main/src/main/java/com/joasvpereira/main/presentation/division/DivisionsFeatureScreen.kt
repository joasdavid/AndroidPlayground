package com.joasvpereira.main.presentation.division

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import com.joasvpereira.main.compose.division.CreateBoxPopup
import com.joasvpereira.main.compose.division.CreateItemPopup
import com.joasvpereira.main.compose.division.DivisionContent
import com.joasvpereira.main.compose.division.DivisionCreateButtons
import com.joasvpereira.main.compose.division.DivisionCreateButtonsState
import com.joasvpereira.main.compose.division.DivisionHeader
import com.joasvpereira.main.compose.division.rememberDivisionCreateButtonsState
import com.joasvpereira.main.domain.data.DivisionThemed
import com.joasvpereira.main.domain.data.DivisionElement
import com.joasvpereira.main.presentation.icons.DivisionIcons
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.ThemeOption
import pt.joasvpereira.coreui.dialog.AlertDialogWithSingleButton
import pt.joasvpereira.coreui.dialog.DialogWithTwoButton
import pt.joasvpereira.coreui.preview.ThemesProvider
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.scaffold.AppScaffold
import pt.joasvpereira.coreui.scaffold.ToolBarConfig


@Composable
fun DivisionsFeatureScreen(
    viewModel: DivisionsFeatureViewModel,
    navController: NavController?
) {
    if (viewModel.divisionId < 0) {
        AlertDialogWithSingleButton(
            onDismissRequest = { },
            buttonText = "Close",
            indicatorColor = MaterialTheme.colorScheme.error,
            onButtonClick = { navController?.popBackStack() },
            buttonColor = MaterialTheme.colorScheme.error
        ) {
            Box(modifier = Modifier.padding(vertical = 10.dp)) {
                Text(text = "No division found . . . .")
            }
        }
        return
    }

    AnimatedVisibility(visible = viewModel.state.filter.isVisible) {
        DynamicTheme(viewModel.state.division.themeOption) {
            AlertDialogWithSingleButton(
                onDismissRequest = viewModel.hideFilter(),
                indicatorIcon = Icons.Default.List,
                buttonText = "Close",
                onButtonClick = viewModel.hideFilter()
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = viewModel.state.filter.selectedFilter == 0, onClick = viewModel.changeFilterSelections(0))
                        SimpleSpace(size = 10.dp)
                        Text(text = "Show all")
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = viewModel.state.filter.selectedFilter == 1, onClick = viewModel.changeFilterSelections(1))
                        SimpleSpace(size = 10.dp)
                        Text(text = "Show only boxes")
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = viewModel.state.filter.selectedFilter == 2, onClick = viewModel.changeFilterSelections(2))
                        SimpleSpace(size = 10.dp)
                        Text(text = "Show only items")
                    }
                }
            }
        }
    }

    AnimatedVisibility(visible = viewModel.state.createBox.isVisible) {
        DynamicTheme(viewModel.state.division.themeOption) {
            CreateBoxPopup(
                onDismissRequest = viewModel.dismissBoxPopup(),
                onButtonPositiveClick = viewModel.saveNewBox(),
                onButtonNegativeClick = viewModel.dismissBoxPopup(),
                boxName = viewModel.state.createBox.name,
                onBoxNameChange = viewModel.onBoxNameChanged(),
                description = viewModel.state.createBox.description,
                onDescriptionChange = viewModel.onBoxDescriptionChanged()
            )
        }
    }

    AnimatedVisibility(visible = viewModel.state.createItem.isVisible) {
        DynamicTheme(viewModel.state.division.themeOption) {
            CreateItemPopup(
                onDismissRequest = viewModel.dismissItemPopup(),
                onButtonPositiveClick = viewModel.saveNewItem(),
                onButtonNegativeClick = viewModel.dismissItemPopup(),
                itemName = viewModel.state.createItem.name,
                onItemNameChange = viewModel.onItemNameChanged(),
                description = viewModel.state.createItem.description,
                onDescriptionChange = viewModel.onItemDescriptionChanged()
            )
        }
    }

    DivisionsScreen(
        divisionThemed = viewModel.state.division,
        divisionListContent = viewModel.state.listContent,
        itemCount = viewModel.state.nrOfItems,
        boxCount = viewModel.state.nrOfBoxes,
        onBackClick = { navController?.popBackStack() },
        onFilterClick = { viewModel.showFilter() },
        isLoading = viewModel.state.isLoading,
        shouldDisplayWithoutAnimation = false, // only for tests and previews
        divisionCreateButtonsState = viewModel.state.createButtonsState,
        onAddBoxClick = viewModel.showCreateBox(),
        onAddItemClick = viewModel.showCreateItem()
    )
}

@Composable
fun DivisionsScreen(
    divisionThemed: DivisionThemed,
    divisionListContent: List<DivisionElement> = emptyList(),
    boxCount: Int = 0,
    itemCount: Int = 0,
    onBackClick: () -> Unit,
    onFilterClick: () -> Unit,
    isLoading: Boolean,
    shouldDisplayWithoutAnimation: Boolean = false,
    divisionCreateButtonsState: DivisionCreateButtonsState = rememberDivisionCreateButtonsState(),
    onAddBoxClick: () -> Unit,
    onAddItemClick: () -> Unit
) {
    DynamicTheme(divisionThemed.themeOption) {
        AppScaffold(
            toolBarConfig = ToolBarConfig(
                onLeftIconClick = onBackClick,
                title = divisionThemed.name,
                rightIcon = Icons.Default.List,
                onRightIconClick = onFilterClick,
                horizontalPadding = 20.dp
            ),
            paddingValues = PaddingValues(0.dp),
            isLoading = isLoading
        ) {
            Box {
                Column {
                    DivisionHeader(
                        shieldImg = divisionThemed.icon.resId,
                        nrFolders = boxCount,
                        nrItems = itemCount,
                        modifier = Modifier.padding(PaddingValues()),
                        shouldDisplayWithoutAnimation = shouldDisplayWithoutAnimation
                    )
                    DivisionContent(divisionListContent, listBottomPadding = it.calculateBottomPadding())
                }
                DivisionCreateButtons(
                    divisionCreateButtonsState = divisionCreateButtonsState,
                    onAddItemClick = onAddItemClick,
                    onAddBoxClick = onAddBoxClick,
                    bottomEdgePadding = it.calculateBottomPadding()
                )
            }
        }
    }
}

private val previewData = mutableListOf<DivisionElement>().apply {
    (1..40).forEach {
        if (it % 2 == 0) {
            add(DivisionElement.Item(name = "name $it", id = it))
        } else {
            add(DivisionElement.Box(name = "name $it", id = it))
        }
    }
}

@Preview(group = "Single")
@Composable
private fun DivisionsScreenSinglePreview() {
    DivisionsScreen(
        divisionThemed = DivisionThemed(
            id = 0,
            name = "Living Room",
            description = null,
            icon = DivisionIcons.livingRoom,
            themeOption = ThemeOption.THEME_DEFAULT
        ),
        onBackClick = {},
        isLoading = false,
        divisionListContent = previewData,
        //divisionListContent = emptyList(),
        shouldDisplayWithoutAnimation = true,
        divisionCreateButtonsState = rememberDivisionCreateButtonsState(isOpen = true),
        onAddBoxClick = {},
        onAddItemClick = {},
        boxCount = 0,
        itemCount = 0,
        onFilterClick = {},
    )
}

@UiModePreview
@Composable
private fun DivisionsScreenPreview(@PreviewParameter(ThemesProvider::class) theme: ThemeOption) {
    DivisionsScreen(
        divisionThemed = DivisionThemed(
            id = 0,
            name = "Living Room",
            description = null,
            icon = DivisionIcons.livingRoom,
            themeOption = theme
        ),
        onBackClick = {},
        isLoading = false,
        divisionListContent = previewData,
        shouldDisplayWithoutAnimation = true,
        divisionCreateButtonsState = rememberDivisionCreateButtonsState(isOpen = true),
        onAddBoxClick = {},
        onAddItemClick = {},
        boxCount = 0,
        itemCount = 0,
        onFilterClick = {},
    )
}