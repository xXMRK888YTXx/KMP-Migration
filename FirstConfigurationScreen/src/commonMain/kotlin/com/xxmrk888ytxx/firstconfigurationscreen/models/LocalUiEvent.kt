package com.xxmrk888ytxx.firstconfigurationscreen.models

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.ui.text.input.TextFieldValue
import com.xxmrk888ytxx.shared.Navigator
import com.xxmrk888ytxx.shared.mvi.UiEvent
import kotlinx.coroutines.CoroutineScope

sealed class LocalUiEvent : UiEvent {

    data class PasswordFromCalculatorInput(val text: TextFieldValue) : LocalUiEvent()

    data class RepeatPasswordFromCalculatorInput(val text: TextFieldValue) : LocalUiEvent()

    class ToSecureSpaceSetup @OptIn(ExperimentalFoundationApi::class) constructor(
        val pager: PagerState,
        val scope: CoroutineScope,
    ) : LocalUiEvent()

    data class PasswordFromSecureSpace(val text: TextFieldValue) : LocalUiEvent()

    data class RepeatPasswordFromSecureSpace(val text: TextFieldValue) : LocalUiEvent()

    class FinalConfigurationEvent(val navigator: Navigator) : LocalUiEvent()
}
