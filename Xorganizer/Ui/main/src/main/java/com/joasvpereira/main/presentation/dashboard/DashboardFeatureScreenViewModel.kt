package com.joasvpereira.main.presentation.dashboard

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joasvpereira.main.domain.data.DashboardDivision
import com.joasvpereira.main.domain.usecase.division.DeleteDivisionParam
import com.joasvpereira.main.domain.usecase.division.IDeleteDivisionUseCase
import com.joasvpereira.main.domain.usecase.division.IDivisionsUseCase
import com.joasvpereira.main.presentation.icons.DivisionIcon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pt.joasvpereira.core.domain.usecase.EmptyParams

class DashboardFeatureScreenViewModel(
    private val sessionName: String?,
    private val sessionImage: Bitmap?,
    private val divisionsUseCase: IDivisionsUseCase,
    private val deleteUseCase: IDeleteDivisionUseCase,
    ): ViewModel() {
    private var _state = mutableStateOf(DashboardFeatureScreenState())
    val state: DashboardFeatureScreenState
        get() = _state.value

    init {
        viewModelScope.launch {
            fetchDivisions()
        }
    }

    private suspend fun fetchDivisions() {
        delay(3000)
        val list = divisionsUseCase.execute(EmptyParams())
        _state.value = state.copy(
            isLoading = false,
            sessionName = sessionName ?: "",
            sessionImage = sessionImage,
            divisions = list
        )
    }

    fun askToDelete(division: DashboardDivision, confirmationName: String = "") {
        _state.value = _state.value.copy(
            deleteEvent = DeleteEvent(
                division = division,
                confirmationText = confirmationName,
            )
        )
    }

    fun cancelDelete() {
        _state.value = _state.value.copy(
            deleteEvent = null
        )
    }

    fun deleteDivision() {
        _state.value = _state.value.copy(isLoading = true)
        _state.value.deleteEvent?.let {
            viewModelScope.launch(Dispatchers.IO) {
                deleteUseCase.execute(DeleteDivisionParam(it.division.id)).also {
                    cancelDelete()
                }
                fetchDivisions()
            }
        }
    }
}