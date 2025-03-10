package com.jedev.simulator.simulator.order.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return sdf.format(Date(timestamp))
}

fun formatPrice(price: Double): String {
    return "R$ " + "%.2f".format(price)
}