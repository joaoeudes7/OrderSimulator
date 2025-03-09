package com.jedev.simulator.simulator.order.domain.useCases

import com.jedev.simulator.simulator.order.domain.models.OrderModel
import com.jedev.simulator.simulator.order.domain.repositories.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetOrdersUseCase {
    operator fun invoke(): Flow<List<OrderModel>>
}

class GetOrdersUseCaseImpl(
    private val repository: OrderRepository
) : GetOrdersUseCase {
    override operator fun invoke() = flow {
        emit(repository.getOrders())
    }
}