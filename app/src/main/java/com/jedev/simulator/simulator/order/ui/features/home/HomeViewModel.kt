package com.jedev.simulator.simulator.order.ui.features.home

import androidx.lifecycle.viewModelScope
import com.jedev.simulator.simulator.order.base.BaseViewModel
import com.jedev.simulator.simulator.order.domain.useCases.GetOrdersUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getOrdersUseCase: GetOrdersUseCase
) : BaseViewModel<HomeUiState>(HomeUiState()) {

    fun fetchOrders() = viewModelScope.launch {
        getOrdersUseCase.invoke()
            .catch {  }
            .collect {
                uiState.orders = it
            }
    }


}