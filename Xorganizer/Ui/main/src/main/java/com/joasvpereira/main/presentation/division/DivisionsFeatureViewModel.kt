package com.joasvpereira.main.presentation.division

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joasvpereira.main.domain.data.DivisionThemed
import com.joasvpereira.main.domain.data.DivisionsContentListItem
import com.joasvpereira.main.domain.data.ItemsAndBoxes
import com.joasvpereira.main.domain.usecase.division.DivisionIdParam
import com.joasvpereira.main.domain.usecase.division.IDivisionUseCase
import com.joasvpereira.main.presentation.icons.DivisionIcons
import com.joasvpereira.main.repository.BoxDataSource
import com.joasvpereira.main.repository.ItemDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.repository.local.entities.Box
import pt.joasvpereira.core.repository.local.entities.Item
import pt.joasvpereira.coreui.ThemeOption

class DivisionsFeatureViewModel(
    val divisionUseCase : IDivisionUseCase,
    val boxDataSource: BoxDataSource,
    val itemDataSource: ItemDataSource,
) : ViewModel() {

    private var _state = mutableStateOf(
        DivisionsFeatureScreenState(
            division = DivisionThemed(id = 0, name = "", description = null, icon = DivisionIcons.home, themeOption = ThemeOption.THEME_BLUE),
            isLoading = true
        )
    )

    val state: DivisionsFeatureScreenState
        get() = _state.value

    private var divisionId: Int = -1

    fun loadDivision(id: Int) {
        divisionId = id
        viewModelScope.launch {
            divisionUseCase.execute(DivisionIdParam(id)).let { division ->
                withContext(Dispatchers.Main) {
                    _state.value = state.copy(
                        division = division
                    )
                }
            }

            fetchList().let { list ->
                withContext(Dispatchers.Main) {
                    _state.value = state.copy(
                        isLoading = false,
                        listContent = list
                    )
                }
            }
        }
    }

    fun dismissBoxPopup(): () -> Unit = {
        _state.value = state.copy(createBox = state.createBox.copy(isVisible = false))
    }

    fun saveNewBox(): () -> Unit = {
        viewModelScope.launch {
            boxDataSource.createNewBox(
                Box(
                    name = state.createBox.name,
                    description = state.createBox.description,
                    parentDivisionId = divisionId
                )
            ).also {
                _state.value = state.copy(
                    createBox = state.createBox.copy(isVisible = false)
                )
            }.also {
                fetchList().run {
                    _state.value = state.copy(
                        listContent = this
                    )
                }
            }
        }
    }

    private suspend fun fetchList(): List<DivisionsContentListItem> = boxDataSource.getBoxes(divisionId).map { box ->
        DivisionsContentListItem.Box(
            id = box.id!!,
            name = box.name
        )
    }.let {boxList ->
        val itemsList = itemDataSource.getDivisionItems(divisionId).map {
            DivisionsContentListItem.Item(
                id = it.id!!,
                name = it.name
            )
        }
        ItemsAndBoxes(list = boxList.plus(itemsList), nrBoxes = boxList.size, nrItems = itemsList.size)
    }.also {
        _state.value = state.copy(
            nrOfBoxes = it.nrBoxes,
            nrOfItems = it.nrItems
        )
    }.list

    fun onBoxNameChanged(): (String) -> Unit = {
        val currBoxState = state.createBox
        _state.value = state.copy(
            createBox = currBoxState.copy(name = it)
        )
    }

    fun onBoxDescriptionChanged(): (String) -> Unit = {
        val currBoxState = state.createBox
        _state.value = state.copy(
            createBox = currBoxState.copy(description = it)
        )
    }

    fun dismissItemPopup(): () -> Unit = {
        _state.value = state.copy(createItem = state.createItem.copy(isVisible = false))
    }

    fun saveNewItem(): () -> Unit = {
        viewModelScope.launch {
            itemDataSource.createNewItem(
                Item(
                    name = state.createItem.name,
                    description = state.createItem.description,
                    parentDivisionId = divisionId
                )
            ).also {
                _state.value = state.copy(
                    createItem = state.createItem.copy(isVisible = false)
                )
            }.also {
                fetchList().run {
                    _state.value = state.copy(
                        listContent = this
                    )
                }
            }
        }
    }

    fun onItemNameChanged(): (String) -> Unit = {
        _state.value = state.copy(createItem = state.createItem.copy(name = it))
    }

    fun onItemDescriptionChanged(): (String) -> Unit = {
        _state.value = state.copy(createItem = state.createItem.copy(description = it))
    }

    fun showCreateBox(): () -> Unit = {
        _state.value = state.copy(
            createBox = DivisionsFeatureScreenState.CreateBox("", "", isVisible = true),
        )
        _state.value.createButtonsState.toggle()
    }

    fun showCreateItem(): () -> Unit = {
        _state.value = state.copy(
            createItem = DivisionsFeatureScreenState.CreateItem(name = "", description = "", isVisible = true),
        )
        _state.value.createButtonsState.toggle()
    }
}