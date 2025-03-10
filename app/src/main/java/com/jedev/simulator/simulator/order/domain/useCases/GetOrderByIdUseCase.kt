package com.jedev.simulator.simulator.order.domain.useCases

import com.jedev.simulator.simulator.order.domain.models.OrderModel
import com.jedev.simulator.simulator.order.domain.repositories.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface GetOrderByIdUseCase {
    operator fun invoke(orderId: Int): Flow<OrderModel>
}

class GetOrderByIdUseCaseImpl(
    private val repository: OrderRepository
) : GetOrderByIdUseCase {
    override operator fun invoke(orderId: Int) = flow {
        emit(repository.getOrderById(orderId))
    }.flowOn(Dispatchers.IO)
}