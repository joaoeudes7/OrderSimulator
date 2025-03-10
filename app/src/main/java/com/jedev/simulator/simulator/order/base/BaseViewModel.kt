package com.jedev.simulator.simulator.order.base

import androidx.lifecycle.ViewModel

interface UiState

open class BaseViewModel<S>(
    val uiState: S
) : ViewModel()