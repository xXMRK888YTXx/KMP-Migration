package com.xxmrk888ytxx.logininsecurespacescreen.models

import com.xxmrk888ytxx.coreandroid.ShareInterfaces.Navigator
import com.xxmrk888ytxx.shared.mvi.UiEvent

sealed class LocalUiEvent : UiEvent {
    
    data class InputEvent(val text:String) : LocalUiEvent()

    class UnlockEvent(val navigator: Navigator) : LocalUiEvent()
}
