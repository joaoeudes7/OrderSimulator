package com.jedev.simulator.simulator.order.ui.features.editOrder

import androidx.lifecycle.viewModelScope
import com.jedev.simulator.simulator.order.base.BaseViewModel
import com.jedev.simulator.simulator.order.domain.models.ItemModel
import com.jedev.simulator.simulator.order.domain.useCases.AddOrderUseCase
import com.jedev.simulator.simulator.order.domain.useCases.GetOrderByIdUseCase
import com.jedev.simulator.simulator.order.domain.useCases.UpdateOrderUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class EditOrderScreenViewModel(
    private val addOrderUseCase: AddOrderUseCase,
    private val updateOrderUseCase: UpdateOrderUseCase,
    private val getOrderByIdUseCase: GetOrderByIdUseCase
) : BaseViewModel<EditOrderUiState>(EditOrderUiState()) {

    val events = MutableStateFlow<EditOrderEvents?>(null)

    fun getOrderById(orderId: Int) = viewModelScope.launch {
        getOrderByIdUseCase.invoke(orderId)
            .catch { }
            .collect {
                uiState.order = it
            }
    }

    fun onSelectItem(item: ItemModel) = viewModelScope.launch {
        uiState.selectedItemIndex = uiState.order.items.indexOf(item)
        uiState.currentItemModelSelected = item
    }

    fun onRemoveCurrentItem() = viewModelScope.launch {
        uiState.order = uiState.order.let {
            it.copy(
                items = it.items - uiState.currentItemModelSelected!!
            )
        }

        closeModal()
    }

    fun onIncreaseQuantity() = viewModelScope.launch {
        uiState.currentItemModelSelected = uiState.currentItemModelSelected?.let {
            it.copy(quantity = it.quantity + 1)
        }
    }

    fun onDecreaseQuantity() = viewModelScope.launch {
        uiState.currentItemModelSelected = uiState.currentItemModelSelected?.let {
            it.copy(quantity = it.quantity - 1)
        }
    }

    fun onChangeClientName(name: String) = viewModelScope.launch {
        uiState.order = uiState.order.copy(clientName = name)
    }

    fun onChangeDescriptionItem(description: String) = viewModelScope.launch {
        uiState.currentItemModelSelected = uiState.currentItemModelSelected?.copy(
            description = description
        )
    }

    fun onChangeUnitPrice(price: String) = viewModelScope.launch {
        uiState.currentItemModelSelected = uiState.currentItemModelSelected?.copy(
            unitPrice = price.trim().replace("\n", "").replace(",", "").toDouble()
        )
    }

    fun onSaveOrder() = viewModelScope.launch {
        val jobOrder = if (uiState.order.id == 0) {
            addOrderUseCase.invoke(uiState.order)
        } else {
            updateOrderUseCase.invoke(uiState.order)
        }

        jobOrder
            .catch { }
            .collect {
                events.emit(EditOrderEvents.OnSavedOrder)
            }
    }

    fun closeModal() = viewModelScope.launch {
        uiState.selectedItemIndex = null
        uiState.currentItemModelSelected = null

        events.emit(EditOrderEvents.OnHiddenModal)
    }

    fun onAddItem() {
        ItemModel().also {
            uiState.currentItemModelSelected = it
        }
    }

    fun onSaveItem() = viewModelScope.launch {
        uiState.order = uiState.order.copy(
            items = uiState.order.items.toMutableList().apply {
                if (uiState.selectedItemIndex != null) {
                    this[uiState.selectedItemIndex!!] = uiState.currentItemModelSelected!!
                } else {
                    this.add(uiState.currentItemModelSelected!!)
                }
            }
        )

        closeModal()
    }

}