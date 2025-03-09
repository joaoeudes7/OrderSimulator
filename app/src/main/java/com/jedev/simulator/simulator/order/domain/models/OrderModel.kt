package com.jedev.simulator.simulator.order.domain.models

data class OrderModel(
    val id: Int? = null,
    val date: Long = System.currentTimeMillis(),
    val items: List<ItemModel> = emptyList()
) {
    val totalAmount: Double
        get() = items.sumOf { it.totalPrice }
}
