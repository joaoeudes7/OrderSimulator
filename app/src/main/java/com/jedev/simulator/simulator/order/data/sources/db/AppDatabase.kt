package com.jedev.simulator.simulator.order.data.sources.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jedev.simulator.simulator.order.data.sources.db.entities.ItemEntity
import com.jedev.simulator.simulator.order.data.sources.db.entities.OrderEntity

@Database(entities = [OrderEntity::class, ItemEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao

    companion object {
        fun createInstance(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "order_database"
        ).build()
    }
}