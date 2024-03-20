package com.xxmrk888ytxx.logininsecurespacescreen.models

import com.xxmrk888ytxx.coreandroid.ShareInterfaces.MVI.UiEvent
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.Navigator

sealed class LocalUiEvent : UiEvent {
    
    data class InputEvent(val text:String) : LocalUiEvent()

    class UnlockEvent(val navigator: Navigator) : LocalUiEvent()
}
