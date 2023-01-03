package pt.joasvpereira.sessionfeature.presentation.select.session

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.joasvpereira.core.domain.usecase.EmptyParams
import pt.joasvpereira.sessionfeature.domain.usecase.ISessionsUseCase

class SelectSessionViewModel(private val sessionUseCase: ISessionsUseCase): ViewModel() {

    val state = mutableStateOf(SelectSessionFeatureState(isLoading = true))

    fun load() {
        state.value = state.value.copy(isLoading = true)
        viewModelScope.launch {
            val list = sessionUseCase.execute(EmptyParams())
            state.value = state.value.copy(sessions = list, isLoading = false)
        }
    }

}