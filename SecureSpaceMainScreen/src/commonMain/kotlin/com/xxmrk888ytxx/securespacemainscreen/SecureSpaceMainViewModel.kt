package com.xxmrk888ytxx.securespacemainscreen

import com.xxmrk888ytxx.securespacemainscreen.models.ScreenState
import com.xxmrk888ytxx.shared.mvi.MultiplatformViewModel
import com.xxmrk888ytxx.shared.mvi.UiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SecureSpaceMainViewModel : MultiplatformViewModel<ScreenState>() {
    override fun onNewEvent(event: UiEvent) = Unit

    override val state: Flow<ScreenState> = flowOf(ScreenState())

    override val defaultValue: ScreenState
        get() = cashedScreenState

    private var cashedScreenState = ScreenState()
}