package com.jedev.simulator.simulator.order.domain.models

import com.jedev.simulator.simulator.order.utils.formatDate
import com.jedev.simulator.simulator.order.utils.formatPrice

data class OrderModel(
    val id: Int = 0,
    var clientName: String = "",
    val date: Long = System.currentTimeMillis(),
    val items: List<ItemModel> = listOf()
) {
    val totalAmount: Double
        get() = items.sumOf { it.totalPrice }

    val dateFormatted: String
        get() = formatDate(date)

    val totalFormatted: String
        get() = formatPrice(totalAmount)
}
