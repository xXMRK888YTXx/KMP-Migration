package com.xxmrk888ytxx.shared

import androidx.lifecycle.ViewModel
import com.xxmrk888ytxx.shared.mvi.UiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class AndroidUiModel<STATE> : ViewModel(), UiModel<STATE> {
    protected val _state by lazy { MutableStateFlow(defaultValue) }
    override val state: Flow<STATE> = _state.asStateFlow()
}