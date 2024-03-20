package com.xxmrk888ytxx.securespacemainscreen

import androidx.lifecycle.ViewModel
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.MVI.UiEvent
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.MVI.UiModel
import com.xxmrk888ytxx.securespacemainscreen.models.ScreenState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SecureSpaceMainViewModel @Inject constructor(

) : ViewModel(),UiModel<ScreenState> {
    override fun handleEvent(event: UiEvent) {

    }

    override val state: Flow<ScreenState> = flowOf(ScreenState())

    override val defValue: ScreenState
        get() = cashedScreenState

    private var cashedScreenState = ScreenState()
}