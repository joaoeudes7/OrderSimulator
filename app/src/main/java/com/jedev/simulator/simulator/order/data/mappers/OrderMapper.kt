package com.jedev.simulator.simulator.order.data.mappers

import com.jedev.simulator.simulator.order.data.sources.db.entities.ItemEntity
import com.jedev.simulator.simulator.order.data.sources.db.entities.OrderEntity
import com.jedev.simulator.simulator.order.data.sources.db.entities.OrderWithItems
import com.jedev.simulator.simulator.order.domain.models.ItemModel
import com.jedev.simulator.simulator.order.domain.models.OrderModel

fun OrderModel.toEntity(): OrderEntity {
    return OrderEntity(
        id = id,
        date = date,
        clientName = clientName
    )
}

fun ItemModel.toEntity(orderId: Int?): ItemEntity {
    return ItemEntity(
        id = id ?: 0,
        description = description,
        quantity = quantity,
        unitPrice = unitPrice,
        orderId = orderId ?: 0,
    )
}

fun ItemEntity.toModel(): ItemModel {
    return ItemModel(
        id = id,
        description = description,
        quantity = quantity,
        unitPrice = unitPrice,
    )
}

fun OrderWithItems.toModel(): OrderModel {
    return OrderModel(
        id = order.id,
        date = order.date,
        clientName = order.clientName,
        items = items.map { it.toModel() }.toMutableList()
    )
}