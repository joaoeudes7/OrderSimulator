package com.jedev.simulator.simulator.order.domain.useCases

import com.jedev.simulator.simulator.order.domain.models.OrderModel
import com.jedev.simulator.simulator.order.domain.repositories.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface AddOrderUseCase {
    operator fun invoke(order: OrderModel): Flow<Int>
}

class AddOrderUseCaseImpl(
    private val repository: OrderRepository
): AddOrderUseCase {
    override operator fun invoke(order: OrderModel) = flow {
        emit(repository.insertOrderWithItems(order))
    }.flowOn(Dispatchers.IO)
}