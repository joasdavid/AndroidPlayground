package com.joasvpereira.main.presentation.division

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import com.joasvpereira.main.compose.common.popup.CreateBoxPopup
import com.joasvpereira.main.compose.common.popup.CreateItemPopup
import com.joasvpereira.main.compose.division.DivisionContent
import com.joasvpereira.main.compose.division.DivisionCreateButtons
import com.joasvpereira.main.compose.division.DivisionCreateButtonsState
import com.joasvpereira.main.compose.division.DivisionHeader
import com.joasvpereira.main.compose.division.DivisionsContentAction
import com.joasvpereira.main.compose.division.popup.FilterPopup
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
import pt.joasvpereira.coreui.text.field.AppTextField


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

    AnimatedVisibility(visible = viewModel.state.deleteEvent.isVisible) {
            DialogWithTwoButton(
                onDismissRequest = { viewModel.hideDeleteConfirmation() },
                indicatorIcon = Icons.Default.Delete,
                indicatorColor = MaterialTheme.colorScheme.error,
                buttonPositiveText = "DELETE",
                buttonPositiveColor = MaterialTheme.colorScheme.error,
                isButtonPositiveEnabled = viewModel.state.deleteEvent.confirmation.toUpperCase(Locale.current) == viewModel.state.deleteEvent.name,
                onButtonPositiveClick = { viewModel.deleteElement() },
                buttonNegativeText = "CANCEL",
                buttonNegativeColor = MaterialTheme.colorScheme.surfaceVariant,
                onButtonNegativeClick = { viewModel.hideDeleteConfirmation() }) {

                val nameUppercase = viewModel.state.deleteEvent.confirmation.toUpperCase(Locale.current)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "To delete division write it's name in all caps \n \"$nameUppercase", textAlign = TextAlign.Center)
                    SimpleSpace(size = 20.dp)
                    AppTextField(
                        value = viewModel.state.deleteEvent.name,
                        onValueChange = { viewModel.deletePopupNameChange(it) },
                        placeholder = "",
                        keyboardOptions = KeyboardOptions(autoCorrect = false, imeAction = ImeAction.Send),
                        keyboardActions = KeyboardActions(onSend = { viewModel.deleteElement() })
                    )
                    SimpleSpace(size = 20.dp)
                }
        }
    }

    AnimatedVisibility(visible = viewModel.state.filter.isVisible) {
        FilterPopup(
            themeOption = viewModel.state.division.themeOption,
            onDismissRequest = viewModel.hideFilter(),
            onButtonClick = viewModel.hideFilter(),
            filterOptionsOption = viewModel.state.filter.selectedFilter,
            onFilterOptionChange = {
                viewModel.changeFilterSelections(it)
            }
        )
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
                onDescriptionChange = viewModel.onBoxDescriptionChanged(),
                isOnEditMode = viewModel.state.createBox.isEditMode
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
                onDescriptionChange = viewModel.onItemDescriptionChanged(),
                        isOnEditMode = viewModel.state.createItem.isEditMode
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
        onAddItemClick = viewModel.showCreateItem(),
        onDeleteItem = { viewModel.showDeleteConfirmation(it) },
        onEditItem = { viewModel.showEdit(it) },
        onOpenItem = { navController?.navigate("ItemDetailScreen?id=${it.id}") },
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
    onDeleteItem: (DivisionElement) -> Unit,
    onEditItem: (DivisionElement) -> Unit,
    onOpenItem: (DivisionElement) -> Unit,
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
                    DivisionContent(
                        divisionListContent, listBottomPadding = it.calculateBottomPadding(),
                        onClick = { element, action ->
                            when(action) {
                                DivisionsContentAction.Open -> onOpenItem(element)
                                DivisionsContentAction.Edit -> onEditItem(element)
                                DivisionsContentAction.Delete -> onDeleteItem(element)
                            }
                        },
                    )
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
        boxCount = 50,
        itemCount = 50,
        onFilterClick = {},
        onDeleteItem = {},
        onEditItem = {},
        onOpenItem = {},
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
        boxCount = 50,
        itemCount = 50,
        onFilterClick = {},
        onDeleteItem = {},
        onEditItem = {},
        onOpenItem = {},
    )
}