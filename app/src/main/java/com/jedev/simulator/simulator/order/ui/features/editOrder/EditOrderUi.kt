package com.jedev.simulator.simulator.order.ui.features.editOrder

import androidx.compose.runtime.*
import com.jedev.simulator.simulator.order.base.UiState
import com.jedev.simulator.simulator.order.domain.models.ItemModel
import com.jedev.simulator.simulator.order.domain.models.OrderModel

class EditOrderUiState : UiState {
    var order by mutableStateOf(OrderModel())
    var selectedItemIndex by mutableStateOf<Int?>(null)
    var currentItemModelSelected: ItemModel? by mutableStateOf(null)

    val title: String
        get() = if (order.id == 0) "Novo Pedido" else "Pedido #${order.id}"
}

class EditOrderUiActions(
    val onAddItem: () -> Unit = {},
    val onSelectItem: (ItemModel) -> Unit = {},
    val onChangeDescriptionItem: (String) -> Unit = {},
    val onChangeUnitPriceItem: (String) -> Unit = {},
    val onDecreaseQuantityItem: () -> Unit = {},
    val onIncreaseQuantityItem: () -> Unit = {},
    val onRemoveItem: () -> Unit = {},
    val onChangeClientName: (String) -> Unit = {},
    val onSaveOrder: () -> Unit = {},
    val onSaveItem: () -> Unit = {},
)

sealed class EditOrderEvents {
    object OnHiddenModal : EditOrderEvents()
    object OnSavedOrder : EditOrderEvents()
}
