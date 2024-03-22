package com.xxmrk888ytxx.shared.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class MultiplatformUiModel<STATE> : UiModel<STATE> {

    protected open val uiModelScope: CoroutineScope =
        CoroutineScope(SupervisorJob() + Dispatchers.Unconfined)

    open fun onCleared() = uiModelScope.cancel()
}