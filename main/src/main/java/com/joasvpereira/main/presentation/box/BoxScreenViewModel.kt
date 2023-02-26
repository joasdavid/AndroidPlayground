package com.joasvpereira.main.presentation.box

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joasvpereira.loggger.extentions.logThis
import com.joasvpereira.main.compose.common.popup.CreateItemPopupStateHolder
import com.joasvpereira.main.compose.common.popup.DeleteItemPopupStateHolder
import com.joasvpereira.main.domain.data.DivisionElement
import com.joasvpereira.main.domain.data.ParentBox
import com.joasvpereira.main.domain.usecase.division.CreateItemParam
import com.joasvpereira.main.domain.usecase.division.DeleteItemParam
import com.joasvpereira.main.domain.usecase.division.GetBoxElementsParams
import com.joasvpereira.main.domain.usecase.division.GetBoxParams
import com.joasvpereira.main.domain.usecase.division.ICreateItemUseCase
import com.joasvpereira.main.domain.usecase.division.IDeleteItemUseCase
import com.joasvpereira.main.domain.usecase.division.IGetBoxElementsUseCase
import com.joasvpereira.main.domain.usecase.division.IGetBoxUseCase
import com.joasvpereira.main.domain.usecase.division.IUpdateItemUseCase
import com.joasvpereira.main.domain.usecase.division.UpdateItemParam
import kotlinx.coroutines.launch

class BoxScreenViewModel(
    val boxId: Int,
    private val getBoxUseCase: IGetBoxUseCase,
    private val getBoxElementsUseCase: IGetBoxElementsUseCase,
    private val createItemUseCase: ICreateItemUseCase,
    private val deleteItemUseCase: IDeleteItemUseCase,
    private val updateItemUseCase: IUpdateItemUseCase,
) : ViewModel() {

    val deleteItemPopupStateHolder: DeleteItemPopupStateHolder = DeleteItemPopupStateHolder()
    val createOrUpdatePopupState = CreateItemPopupStateHolder(
        isOnEditMode = false,
        isVisible = false,
    )

    val state = StateTemp()

    init {
        viewModelScope.launch {
            state.isLoading = true
            getBoxUseCase.execute(GetBoxParams(boxId)).collect {
                it?.run {
                    state.box = this
                    state.isLoading = false
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            getBoxElementsUseCase.execute(GetBoxElementsParams(boxId)).collect {
                "collecting list with size ${it.size}".logThis(tag = "JVP")
                state.list = it
            }
        }
    }

    init {
        createOrUpdatePopupState.onButtonPositiveClick = {
            createOrUpdatePopupState.apply {
                viewModelScope.launch {
                    if (isOnEditMode) {
                        updateItemUseCase.execute(
                            UpdateItemParam(
                                id = itemIdToUpdate,
                                name = name,
                                description = description,
                            ),
                        )
                    } else {
                        createItemUseCase.execute(
                            CreateItemParam(
                                name = name,
                                description = description,
                                parentId = state.box.parentDivision!!.id,
                                parentBoxId = boxId,
                            ),
                        )
                    }
                }
            }
        }

        deleteItemPopupStateHolder.onButtonPositiveClick = {
            itemIdToDelete?.let { id ->
                viewModelScope.launch {
                    deleteItemUseCase.execute(DeleteItemParam(id))
                }
            }
        }
    }

    private var itemIdToDelete: Int? = null
    fun deleteItem(element: DivisionElement.Item) {
        itemIdToDelete = element.id
        deleteItemPopupStateHolder.confirmationName = element.name
        deleteItemPopupStateHolder.name = ""
        deleteItemPopupStateHolder.isVisible = true
    }

    private var itemIdToUpdate = 0
    fun editItem(element: DivisionElement.Item) {
        createOrUpdatePopupState.apply {
            itemIdToUpdate = element.id
            name = element.name
            description = element.description
            isOnEditMode = true
            isVisible = true
        }
    }
}

class StateTemp {
    var list: List<DivisionElement.Item> by mutableStateOf(emptyList())
    var box: ParentBox by mutableStateOf(ParentBox(id = 0, name = "", description = "", parentDivision = null))
    var isLoading by mutableStateOf(true)
}
