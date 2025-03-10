package com.jedev.simulator.simulator.order

import android.app.Application
import com.jedev.simulator.simulator.order.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OrderSimulatorApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setupDi()
    }

    private fun setupDi() {
        startKoin {
            androidContext(this@OrderSimulatorApplication)
            modules(appModule)
        }
    }
}