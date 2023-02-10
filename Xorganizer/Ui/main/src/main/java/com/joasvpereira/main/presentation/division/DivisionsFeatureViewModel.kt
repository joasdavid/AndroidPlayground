package com.joasvpereira.main.presentation.division

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joasvpereira.loggger.extentions.logThis
import com.joasvpereira.main.compose.division.popup.FilterOptions
import com.joasvpereira.main.domain.data.DivisionElement
import com.joasvpereira.main.domain.data.DivisionThemed
import com.joasvpereira.main.domain.usecase.division.CreateBoxParam
import com.joasvpereira.main.domain.usecase.division.CreateItemParam
import com.joasvpereira.main.domain.usecase.division.DeleteBoxParam
import com.joasvpereira.main.domain.usecase.division.DeleteItemParam
import com.joasvpereira.main.domain.usecase.division.DivisionIdParam
import com.joasvpereira.main.domain.usecase.division.GetDivisionElementsParams
import com.joasvpereira.main.domain.usecase.division.ICreateBoxUseCase
import com.joasvpereira.main.domain.usecase.division.ICreateItemUseCase
import com.joasvpereira.main.domain.usecase.division.IDeleteBoxUseCase
import com.joasvpereira.main.domain.usecase.division.IDeleteItemUseCase
import com.joasvpereira.main.domain.usecase.division.IDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.IGetDivisionElementsUseCase
import com.joasvpereira.main.domain.usecase.division.IUpdateBoxUseCase
import com.joasvpereira.main.domain.usecase.division.IUpdateDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.IUpdateItemUseCase
import com.joasvpereira.main.domain.usecase.division.UpdateBoxParam
import com.joasvpereira.main.domain.usecase.division.UpdateBoxUseCase
import com.joasvpereira.main.domain.usecase.division.UpdateDivisionParam
import com.joasvpereira.main.domain.usecase.division.UpdateDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.UpdateItemParam
import com.joasvpereira.main.domain.usecase.division.UpdateItemUseCase
import com.joasvpereira.main.presentation.icons.DivisionIcons
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.joasvpereira.coreui.ThemeOption

class DivisionsFeatureViewModel(
    var divisionId: Int = -1,
    private val divisionUseCase: IDivisionUseCase,
    private val getDivisionElementsUseCase: IGetDivisionElementsUseCase,
    private val createBoxUseCase: ICreateBoxUseCase,
    private val createItemUseCase: ICreateItemUseCase,
    private val deleteBoxUseCase: IDeleteBoxUseCase,
    private val deleteItemUseCase: IDeleteItemUseCase,
    private val updateItemUseCase: IUpdateItemUseCase,
    private val updateBoxUseCase: IUpdateBoxUseCase,
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

            getDivisionElementsUseCase.execute(GetDivisionElementsParams(divisionId)).collectLatest {
                _state.value = state.copy(
                    listContent = it.list,
                    nrOfItems = it.nrItems,
                    nrOfBoxes = it.nrBoxes
                )
            }

        }
    }

    fun showFilter() {
        _state.value = state.copy(
            filter = state.filter.copy(isVisible = true)
        )
    }

    fun dismissBoxPopup(): () -> Unit = {
        _state.value = state.copy(createBox = state.createBox.copy(isVisible = false))
    }

    fun saveNewBox(): () -> Unit = {
        viewModelScope.launch {
            if (state.createBox.isEditMode){
                updateBoxUseCase.execute(
                    UpdateBoxParam(
                        divisionElement!!.id,
                        name = state.createBox.name,
                        description = state.createBox.description
                    )
                )
            }else {
                createBoxUseCase.execute(
                    CreateBoxParam(
                        name = state.createBox.name,
                        description = state.createBox.description,
                        parentId = divisionId
                    )
                )
            }

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
            if (state.createItem.isEditMode) {
                updateItemUseCase.execute(
                    UpdateItemParam(
                        id = divisionElement!!.id,
                        name = state.createItem.name,
                        description = state.createItem.description,
                    )
                )
            } else {
                createItemUseCase.execute(
                    CreateItemParam(
                        name = state.createItem.name,
                        description = state.createItem.description,
                        parentId = divisionId
                    )
                )
            }
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
            createBox = DivisionsFeatureScreenState.CreateBox(name = "", description = "", isVisible = true),
        )
        _state.value.createButtonsState.toggle()
    }

    fun showEdit(element: DivisionElement) {
        divisionElement = element
        when (element) {
            is DivisionElement.Box -> _state.value = state.copy(
                createBox = DivisionsFeatureScreenState.CreateBox(id = element.id, name = element.name, description = element.description, isVisible = true, isEditMode = true),
            )

            is DivisionElement.Item -> _state.value = state.copy(
                createItem = DivisionsFeatureScreenState.CreateItem(id = element.id, name = element.name, description = element.description, isVisible = true, isEditMode = true),
            )
        }
    }

    fun showCreateItem(): () -> Unit = {
        _state.value = state.copy(
            createItem = DivisionsFeatureScreenState.CreateItem(name = "", description = "", isVisible = true),
        )
        _state.value.createButtonsState.toggle()
    }

    private var divisionElement: DivisionElement? = null
    fun showDeleteConfirmation(divisionElement: DivisionElement) {
        this.divisionElement = divisionElement
        val (name, isBox) = when (divisionElement) {
            is DivisionElement.Box -> Pair(divisionElement.name, true)
            is DivisionElement.Item -> Pair(divisionElement.name, false)
        }
        _state.value = state.copy(
            deleteEvent = DivisionsFeatureScreenState.DeleteEvent(
                isBox = isBox, confirmation = name, name = "", description = "", isVisible = true

            )
        )
    }

    fun hideDeleteConfirmation() {
        _state.value = state.copy(
            deleteEvent = state.deleteEvent.copy(isVisible = false)
        )
    }

    fun deletePopupNameChange(name: String) {
        state.deleteEvent.run {
            _state.value = state.copy(
                deleteEvent = this.copy(
                    name = name
                )
            ).logThis("DivisionScreen")
        }
    }

    fun deleteElement() {
        divisionElement?.let { element ->
            viewModelScope.launch {
                when (element) {
                    is DivisionElement.Box -> deleteBoxUseCase.execute(DeleteBoxParam(element.id))
                    is DivisionElement.Item -> deleteItemUseCase.execute(DeleteItemParam(element.id))
                }
                _state.value = state.copy(
                    deleteEvent = state.deleteEvent.copy(isVisible = false)
                )
            }
        }
    }

    fun hideFilter(): () -> Unit = {
        _state.value = state.copy(
            filter = state.filter.copy(isVisible = false)
        )
    }

    fun changeFilterSelections(selectedOption: FilterOptions) {
        _state.value = state.copy(
            filter = state.filter.copy(
                selectedFilter = selectedOption
            )
        )
        applyFilter()
    }

    private fun applyFilter() {
        viewModelScope.launch {
            getDivisionElementsUseCase.execute(
                GetDivisionElementsParams(
                    divisionId = divisionId,
                    filter = when (state.filter.selectedFilter) {
                        FilterOptions.OnlyBox -> GetDivisionElementsParams.Filter.OnlyBoxes
                        FilterOptions.OnlyItem -> GetDivisionElementsParams.Filter.OnlyItems
                        else -> GetDivisionElementsParams.Filter.All
                    }
                )
            ).collectLatest {
                _state.value = state.copy(
                    listContent = it.list,
                    nrOfBoxes = it.nrBoxes,
                    nrOfItems = it.nrItems
                )
            }
        }
    }
}