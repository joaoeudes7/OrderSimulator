package com.jedev.simulator.simulator.order.domain.models

data class ItemModel(
    val id: Int? = null,
    val name: String,
    val quantity: Int,
    val unitPrice: Double
) {
    val totalPrice: Double
        get() = quantity * unitPrice
}
