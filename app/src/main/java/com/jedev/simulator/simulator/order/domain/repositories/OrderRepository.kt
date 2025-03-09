package com.jedev.simulator.simulator.order.domain.repositories

import com.jedev.simulator.simulator.order.domain.models.OrderModel

interface OrderRepository {
    suspend fun getOrders() : List<OrderModel>
    suspend fun insertOrderWithItems(order: OrderModel): Int
    suspend fun editOrderWithItems(order: OrderModel)
}