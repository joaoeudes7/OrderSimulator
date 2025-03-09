package com.jedev.simulator.simulator.order.domain.useCases

import com.jedev.simulator.simulator.order.domain.models.OrderModel
import com.jedev.simulator.simulator.order.domain.repositories.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface UpdateOrderUseCase {
    operator fun invoke(item: OrderModel): Flow<Int?>
}

class UpdateOrderUseCaseImpl(
    private val orderRepository: OrderRepository
): UpdateOrderUseCase {
    override operator fun invoke(item: OrderModel) = flow {
        orderRepository.editOrderWithItems(item)

        emit(item.id)
    }
}