package pt.joasvpereira.sessionfeature.presentation.select.session

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joasvpereira.sessioncore.domail.usecases.ISessionsUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import pt.joasvpereira.core.domain.usecase.EmptyParams

class SelectSessionViewModel(private val sessionUseCase: ISessionsUseCase) : ViewModel() {

    val state = mutableStateOf(SelectSessionFeatureState(isLoading = true))

    init {
        viewModelScope.launch {
            sessionUseCase.execute(EmptyParams()).collectLatest { list ->
                state.value = state.value.copy(sessions = list, isLoading = false)
            }
        }
    }

    fun toggleEditMode() {
        state.value = state.value.copy(isEditMode = !state.value.isEditMode)
    }
}
