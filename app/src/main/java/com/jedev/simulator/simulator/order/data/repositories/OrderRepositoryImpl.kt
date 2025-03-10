package com.jedev.simulator.simulator.order.data.repositories

import com.jedev.simulator.simulator.order.data.mappers.toEntity
import com.jedev.simulator.simulator.order.data.mappers.toModel
import com.jedev.simulator.simulator.order.data.sources.db.AppDatabase
import com.jedev.simulator.simulator.order.domain.models.OrderModel
import com.jedev.simulator.simulator.order.domain.repositories.OrderRepository

class OrderRepositoryImpl(
    private val db: AppDatabase,
) : OrderRepository {

    override suspend fun getOrders(): List<OrderModel> {
        return db.orderDao().getAllOrders().map { it.toModel() }
    }

    override suspend fun getOrderById(orderId: Int): OrderModel {
        return db.orderDao().getOrderById(orderId).toModel()
    }

    override suspend fun insertOrderWithItems(order: OrderModel): Int {
        return db.orderDao().insertOrderWithItems(
            order.toEntity(),
            order.items.map { it.toEntity(order.id) }
        )
    }

    override suspend fun editOrderWithItems(order: OrderModel) {
        db.orderDao().insertOrderWithItems(
            order.toEntity(),
            order.items.map { it.toEntity(order.id) }
        )
    }
}