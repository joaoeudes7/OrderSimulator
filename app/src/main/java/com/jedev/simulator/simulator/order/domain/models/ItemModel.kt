package com.jedev.simulator.simulator.order.domain.models

import com.jedev.simulator.simulator.order.utils.formatPrice

data class ItemModel(
    val id: Int? = null,
    var description: String = "",
    val quantity: Int = 1,
    val unitPrice: Double = 0.0,
    val unitPriceStr: String = "$unitPrice",
) {
    val totalPrice: Double
        get() = quantity * unitPrice

    val totalPriceFormatted: String
        get() = formatPrice(totalPrice)

    val unitPriceFormatted: String
        get() = formatPrice(unitPrice)
}
