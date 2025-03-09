package com.jedev.simulator.simulator.order.data.sources.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val orderId: Int,
    val name: String,
    val quantity: Int,
    val unitPrice: Double
)
