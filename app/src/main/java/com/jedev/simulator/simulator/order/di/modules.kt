package com.jedev.simulator.simulator.order.di

import com.jedev.simulator.simulator.order.data.repositories.OrderRepositoryImpl
import com.jedev.simulator.simulator.order.data.sources.db.AppDatabase
import com.jedev.simulator.simulator.order.domain.repositories.OrderRepository
import com.jedev.simulator.simulator.order.domain.useCases.AddOrderUseCase
import com.jedev.simulator.simulator.order.domain.useCases.AddOrderUseCaseImpl
import com.jedev.simulator.simulator.order.domain.useCases.GetOrderByIdUseCase
import com.jedev.simulator.simulator.order.domain.useCases.GetOrderByIdUseCaseImpl
import com.jedev.simulator.simulator.order.domain.useCases.GetOrdersUseCase
import com.jedev.simulator.simulator.order.domain.useCases.GetOrdersUseCaseImpl
import com.jedev.simulator.simulator.order.domain.useCases.UpdateOrderUseCase
import com.jedev.simulator.simulator.order.domain.useCases.UpdateOrderUseCaseImpl
import com.jedev.simulator.simulator.order.ui.features.editOrder.EditOrderScreenViewModel
import com.jedev.simulator.simulator.order.ui.features.home.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { EditOrderScreenViewModel(get(), get(), get()) }
}

val useCaseModule = module {
    factory<AddOrderUseCase> { AddOrderUseCaseImpl(get()) }
    factory<GetOrdersUseCase> { GetOrdersUseCaseImpl(get()) }
    factory<UpdateOrderUseCase> { UpdateOrderUseCaseImpl(get()) }
    factory<GetOrderByIdUseCase> { GetOrderByIdUseCaseImpl(get()) }
}

val repositoryModule = module {
    single<OrderRepository> { OrderRepositoryImpl(get()) }
}

val sourcesModule = module {
    single { AppDatabase.createInstance(androidApplication()) }
}

val appModule = listOf(
    viewModelModule,
    repositoryModule,
    useCaseModule,
    sourcesModule
)