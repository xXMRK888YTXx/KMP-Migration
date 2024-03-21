package com.xxmrk888ytxx.securespacemainscreen

import androidx.lifecycle.ViewModel
import com.xxmrk888ytxx.securespacemainscreen.models.ScreenState
import com.xxmrk888ytxx.shared.mvi.UiEvent
import com.xxmrk888ytxx.shared.mvi.UiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SecureSpaceMainViewModel @Inject constructor(

) : ViewModel(), UiModel<ScreenState> {
    override fun onNewEvent(event: UiEvent) {

    }

    override val state: Flow<ScreenState> = flowOf(ScreenState())

    override val defaultValue: ScreenState
        get() = cashedScreenState

    private var cashedScreenState = ScreenState()
}