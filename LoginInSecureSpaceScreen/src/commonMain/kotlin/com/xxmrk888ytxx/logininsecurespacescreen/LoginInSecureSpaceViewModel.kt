package com.xxmrk888ytxx.logininsecurespacescreen

import androidx.lifecycle.viewModelScope
import com.xxmrk888ytxx.logininsecurespacescreen.contracts.CheckPasswordFromSecureSpaceContract
import com.xxmrk888ytxx.logininsecurespacescreen.models.LocalUiEvent
import com.xxmrk888ytxx.logininsecurespacescreen.models.ScreenState
import com.xxmrk888ytxx.shared.mvi.MultiplatformViewModel
import com.xxmrk888ytxx.shared.mvi.UiEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginInSecureSpaceViewModel(
    private val checkPasswordFromSecureSpaceContract: CheckPasswordFromSecureSpaceContract
) : MultiplatformViewModel<ScreenState>() {

    override fun onNewEvent(event: UiEvent) {
        if(event !is LocalUiEvent) return

        when(event) {
            is LocalUiEvent.InputEvent -> {
                inputTextState.update { event.text }
            }

            is LocalUiEvent.UnlockEvent -> {
                viewModelScope.launch {
                    if(checkPasswordFromSecureSpaceContract.checkPassword(inputTextState.value)) {
                        withContext(Dispatchers.Main) {
                            event.navigator.toSecureSpaceMainScreen()
                        }
                    } else {
                        event.snackbarHost.showSnackbar("Incorrect password")
                    }
                }
            }
        }
    }

    private val inputTextState = MutableStateFlow("")

    override val state: Flow<ScreenState> = inputTextState.map { text ->
        ScreenState(
            enteredPassword = text
        ).also { cashedScreenState = it }
    }

    private var cashedScreenState = ScreenState()

    override val defaultValue: ScreenState
        get() = cashedScreenState
}