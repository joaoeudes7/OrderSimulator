package com.jedev.simulator.simulator.order.data.sources.db

import androidx.room.*
import com.jedev.simulator.simulator.order.data.sources.db.entities.ItemEntity
import com.jedev.simulator.simulator.order.data.sources.db.entities.OrderEntity
import com.jedev.simulator.simulator.order.data.sources.db.entities.OrderWithItems

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrderEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<ItemEntity>)

    @Transaction
    suspend fun insertOrderWithItems(order: OrderEntity, items: List<ItemEntity>): Int {
        val orderId = insertOrder(order).toInt()
        val itemsWithOrderId = items.map { it.copy(orderId = orderId) }

        insertItems(itemsWithOrderId)

        return orderId
    }

    @Query("SELECT * FROM OrderEntity ORDER BY date DESC")
    suspend fun getAllOrders(): List<OrderWithItems>

    @Query("SELECT * FROM OrderEntity WHERE id = :orderId")
    suspend fun getOrderById(orderId: Int): OrderWithItems

    @Delete
    suspend fun deleteOrder(order: OrderEntity)
}
