package com.xxmrk888ytxx.logininsecurespacescreen.models

import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.text.input.TextFieldValue
import com.xxmrk888ytxx.shared.Navigator
import com.xxmrk888ytxx.shared.mvi.UiEvent

sealed class LocalUiEvent : UiEvent {

    data class InputEvent(val text: TextFieldValue) : LocalUiEvent()

    class UnlockEvent(
        val navigator: Navigator,
        val snackbarHost: SnackbarHostState,
    ) : LocalUiEvent()
}
