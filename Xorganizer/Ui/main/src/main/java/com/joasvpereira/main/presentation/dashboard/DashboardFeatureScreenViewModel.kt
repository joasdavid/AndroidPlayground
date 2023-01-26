package com.joasvpereira.main.presentation.dashboard

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joasvpereira.main.domain.data.DashboardDivision
import com.joasvpereira.main.domain.usecase.division.IDivisionsUseCase
import com.joasvpereira.main.domain.usecase.division.MyDivisionsUseCase
import com.joasvpereira.main.presentation.icons.DivisionIcons
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pt.joasvpereira.core.domain.usecase.EmptyParams
import pt.joasvpereira.coreui.ThemeOption

class DashboardFeatureScreenViewModel(
    private val sessionName: String?,
    private val sessionImage: Bitmap?,
    private val divisionsUseCase: IDivisionsUseCase
    ): ViewModel() {
    private var _state = mutableStateOf(DashboardFeatureScreenState())
    val state: DashboardFeatureScreenState
        get() = _state.value

    init {
        viewModelScope.launch {
            delay(3000)
            val list = divisionsUseCase.execute(EmptyParams())
            _state.value = state.copy(
                isLoading = false,
                sessionName = sessionName ?: "",
                sessionImage = sessionImage,
                divisions = list
            )
        }
    }
}