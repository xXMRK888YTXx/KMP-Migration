package com.xxmrk888ytxx.firstconfigurationscreen.models

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.MVI.UiEvent
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.Navigator
import kotlinx.coroutines.CoroutineScope

sealed class LocalUiEvent : UiEvent {

    data class PasswordFromCalculatorInput(val text:String) : LocalUiEvent()

    data class RepeatPasswordFromCalculatorInput(val text:String) : LocalUiEvent()

    class ToSecureSpaceSetup @OptIn(ExperimentalFoundationApi::class) constructor(val pager: PagerState, val scope: CoroutineScope) : LocalUiEvent()

    data class PasswordFromSecureSpace(val text: String) : LocalUiEvent()

    data class RepeatPasswordFromSecureSpace(val text: String) : LocalUiEvent()

    class FinalConfigurationEvent(val navigator: Navigator) : LocalUiEvent()
}
