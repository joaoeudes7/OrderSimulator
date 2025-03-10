package com.jedev.simulator.simulator.order.ui.features.home

import androidx.compose.runtime.*
import com.jedev.simulator.simulator.order.base.UiState
import com.jedev.simulator.simulator.order.domain.models.OrderModel

class HomeUiState : UiState {
    var orders by mutableStateOf(listOf<OrderModel>())
}

class HomeUiEvents {
}

class HomeUiActions(
    val onOrderClick: (Int) -> Unit = {},
    val onAddOrder: () -> Unit = {}
) {
}