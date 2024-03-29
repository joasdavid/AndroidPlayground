package com.joasvpereira.main.presentation.create

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joasvpereira.main.domain.usecase.division.CreateDivisionParams
import com.joasvpereira.main.domain.usecase.division.DeleteDivisionParam
import com.joasvpereira.main.domain.usecase.division.DivisionIdParam
import com.joasvpereira.main.domain.usecase.division.ICreateDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.IDeleteDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.IDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.IUpdateDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.UpdateDivisionParam
import com.joasvpereira.main.presentation.icons.DivisionIcon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.joasvpereira.coreui.theme.ThemeOption
import java.util.Locale

class CreateDivisionViewModel(
    private val divisionUseCase: IDivisionUseCase,
    private val createDivisionUseCase: ICreateDivisionUseCase,
    private val updateDivisionUseCase: IUpdateDivisionUseCase,
    private val deleteDivisionUseCase: IDeleteDivisionUseCase,
) : ViewModel() {

    private var _state = mutableStateOf(CreateDivisionScreenState())
    val state: CreateDivisionScreenState
        get() = _state.value

    private var internalId: Int = 0

    fun targetDivision(id: Int) {
        internalId = id
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            divisionUseCase.execute(DivisionIdParam(id)).run {
                withContext(Dispatchers.Main) {
                    _state.value = _state.value.copy(
                        icon = icon,
                        name = name,
                        description = description ?: "",
                        theme = themeOption,
                        isLoading = false,
                        mode = Mode.EDIT,
                        deleteData = DeleteData("", name.uppercase(Locale.getDefault()), false),
                    )
                }
            }
        }
    }

    fun iconSelected(divisionIcon: DivisionIcon) {
        _state.value = _state.value.copy(icon = divisionIcon)
    }

    fun themeSelected(theme: ThemeOption) {
        _state.value = _state.value.copy(theme = theme)
    }

    fun save() {
        if (_state.value.mode == Mode.CREATE) {
            createDivision()
        } else {
            updateDivision()
        }
    }

    private fun createDivision() {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            _state.value.run {
                createDivisionUseCase.execute(
                    CreateDivisionParams(
                        name = name,
                        description = description,
                        icon = icon,
                        themeId = theme.id,
                    ),
                )
            }
            _state.value = _state.value.copy(isLoading = false, navigation = CreateDivisionScreenNavigation.SaveDone)
        }
    }

    private fun updateDivision() {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            _state.value.run {
                updateDivisionUseCase.execute(
                    UpdateDivisionParam(
                        id = internalId,
                        name = name,
                        description = description,
                        icon = icon,
                        themeId = theme.id,
                    ),
                )
            }
            _state.value = _state.value.copy(isLoading = false, navigation = CreateDivisionScreenNavigation.SaveDone)
        }
    }

    fun nameChanged(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    fun descriptionChanged(description: String) {
        _state.value = _state.value.copy(description = description)
    }

    fun showDeletePopup() {
        _state.value = _state.value.copy(
            deleteData = _state.value.deleteData.copy(isPopupShowing = true),
        )
    }

    fun hideDeletePopup() {
        _state.value = _state.value.copy(
            deleteData = _state.value.deleteData.copy(isPopupShowing = false),
        )
    }

    fun updateConfirmationNameOnDeletePopup(string: String) {
        _state.value = _state.value.copy(
            deleteData = _state.value.deleteData.copy(name = string),
        )
    }

    fun deleteDivision() {
        if (
            _state.value.deleteData.isNameMatchingConfirmation &&
            internalId > 0
        ) {
            viewModelScope.launch {
                deleteDivisionUseCase.execute(DeleteDivisionParam(id = internalId))
                _state.value = CreateDivisionScreenState(navigation = CreateDivisionScreenNavigation.DeleteDone)
            }
        }
    }
}
