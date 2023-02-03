package com.joasvpereira.main.presentation.division

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joasvpereira.main.domain.data.DivisionThemed
import com.joasvpereira.main.domain.data.DivisionElement
import com.joasvpereira.main.domain.data.DivisionElements
import com.joasvpereira.main.domain.usecase.division.CreateBoxParam
import com.joasvpereira.main.domain.usecase.division.CreateItemParam
import com.joasvpereira.main.domain.usecase.division.DivisionIdParam
import com.joasvpereira.main.domain.usecase.division.GetDivisionElementsUseCase
import com.joasvpereira.main.domain.usecase.division.ICreateBoxUseCase
import com.joasvpereira.main.domain.usecase.division.ICreateItemUseCase
import com.joasvpereira.main.domain.usecase.division.IDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.IGetDivisionElementsUseCase
import com.joasvpereira.main.presentation.icons.DivisionIcons
import com.joasvpereira.main.repository.BoxDataSource
import com.joasvpereira.main.repository.ItemDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.joasvpereira.core.repository.local.entities.Box
import pt.joasvpereira.core.repository.local.entities.Item
import pt.joasvpereira.coreui.ThemeOption

class DivisionsFeatureViewModel(
    var divisionId: Int = -1,
    private val divisionUseCase : IDivisionUseCase,
    private val getDivisionElementsUseCase: IGetDivisionElementsUseCase,
    private val createBoxUseCase: ICreateBoxUseCase,
    private val createItemUseCase: ICreateItemUseCase,
) : ViewModel() {

    private var _state = mutableStateOf(
        DivisionsFeatureScreenState(
            division = DivisionThemed(id = 0, name = "", description = null, icon = DivisionIcons.home, themeOption = ThemeOption.THEME_BLUE)
        )
    )

    val state: DivisionsFeatureScreenState
        get() = _state.value

    init {
        viewModelScope.launch {
            divisionUseCase.execute(DivisionIdParam(divisionId)).let { division ->
                withContext(Dispatchers.Main) {
                    _state.value = state.copy(
                        division = division
                    )
                }
            }

            getDivisionElementsUseCase.execute(DivisionIdParam(divisionId)).collectLatest {
                _state.value = state.copy(
                    listContent = it.list,
                    nrOfItems = it.nrItems,
                    nrOfBoxes = it.nrBoxes
                )
            }

        }
    }

    fun dismissBoxPopup(): () -> Unit = {
        _state.value = state.copy(createBox = state.createBox.copy(isVisible = false))
    }

    fun saveNewBox(): () -> Unit = {
        viewModelScope.launch {
            createBoxUseCase.execute(
                CreateBoxParam(
                    name = state.createBox.name,
                    description = state.createBox.description,
                    parentId = divisionId
                )
            )

            _state.value = state.copy(
                createBox = state.createBox.copy(isVisible = false)
            )
        }
    }

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
            createItemUseCase.execute(
                CreateItemParam(
                    name = state.createItem.name,
                    description = state.createItem.description,
                    parentId = divisionId
                )
            )
            _state.value = state.copy(
                createItem = state.createItem.copy(isVisible = false)
            )
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