package com.xxmrk888ytxx.shared.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class MultiplatformUiModel<STATE> : UiModel<STATE> {

    protected abstract val uiModelScope: CoroutineScope

    protected val _state by lazy { MutableStateFlow(defaultValue) }
    override val state: Flow<STATE> = _state.asStateFlow()

    protected open fun onCleared() {
        uiModelScope.cancel()
    }
}