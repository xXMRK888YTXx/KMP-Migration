package com.xxmrk888ytxx.shared.mvi

import kotlinx.coroutines.flow.Flow

interface UiModel<STATE> {
    val state: Flow<STATE>
    val defaultValue: STATE
    fun onNewEvent(event: UiEvent)
}

