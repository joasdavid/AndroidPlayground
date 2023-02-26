package com.joasvpereira.main.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joasvpereira.loggger.extentions.logThis
import com.joasvpereira.main.compose.common.popup.CreateItemPopupStateHolder
import com.joasvpereira.main.compose.common.popup.DeleteItemPopupStateHolder
import com.joasvpereira.main.compose.details.ItemNotFoundPopupStateHolder
import com.joasvpereira.main.domain.usecase.division.DeleteItemParam
import com.joasvpereira.main.domain.usecase.division.GetItemDetailsParams
import com.joasvpereira.main.domain.usecase.division.IDeleteItemUseCase
import com.joasvpereira.main.domain.usecase.division.IGetItemDetailsUseCase
import com.joasvpereira.main.domain.usecase.division.IUpdateItemUseCase
import com.joasvpereira.main.domain.usecase.division.UpdateItemParam
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ItemDetailScreenViewModel(
    private val itemId: Int,
    getDetails: IGetItemDetailsUseCase,
    updateItemUseCase: IUpdateItemUseCase,
    deleteItemUseCase: IDeleteItemUseCase,
) : ViewModel() {

    private var _state by mutableStateOf(ItemDetailScreenState(isLoading = true))
    val state: ItemDetailScreenState
        get() = _state

    val updateDetailsState = CreateItemPopupStateHolder(
        isOnEditMode = true,
        isVisible = false,
    )

    val deleteItemState = DeleteItemPopupStateHolder(
        isVisible = false,
    )

    val notFoundPopupState = ItemNotFoundPopupStateHolder(
        isVisible = false,
    )

    init {
        viewModelScope.launch {
            getDetails.execute(GetItemDetailsParams(itemId)).collectLatest {
                if (it != null) {
                    _state = state.copy(
                        itemDetail = it.logThis(tag = "DetailsScreen") { value -> "Collecting state: $value" },
                        isLoading = false,
                    )
                    updateDetailsState.name = state.itemDetail.name
                    updateDetailsState.description = state.itemDetail.description
                    deleteItemState.confirmationName = state.itemDetail.name
                    "update popup -- $updateDetailsState".logThis(tag = "DetailsScreen")
                } else {
                    _state = ItemDetailScreenState(isLoading = true)
                    delay(500)
                    notFoundPopupState.isVisible = true
                }
            }
        }
    }

    init {
        updateDetailsState.onButtonPositiveClick = {
            "hi".logThis(tag = "DetailsScreen")
            viewModelScope.launch {
                "Try to update item {$itemId} with values: name = ${updateDetailsState.name}, description = ${updateDetailsState.description}".logThis(tag = "DetailsScreen")
                updateItemUseCase.execute(UpdateItemParam(id = itemId, name = updateDetailsState.name, description = updateDetailsState.description))
            }
        }
    }

    init {
        deleteItemState.onButtonPositiveClick = {
            viewModelScope.launch {
                deleteItemUseCase.execute(DeleteItemParam(itemId))
            }
        }
    }
}
