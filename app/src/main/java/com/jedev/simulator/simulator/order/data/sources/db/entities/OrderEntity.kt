package com.jedev.simulator.simulator.order.data.sources.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: Long,
)
