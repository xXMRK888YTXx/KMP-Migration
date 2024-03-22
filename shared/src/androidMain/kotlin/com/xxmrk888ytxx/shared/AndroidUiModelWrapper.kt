package com.xxmrk888ytxx.shared

import androidx.lifecycle.ViewModel
import com.xxmrk888ytxx.shared.mvi.MultiplatformUiModel
import com.xxmrk888ytxx.shared.mvi.UiEvent
import com.xxmrk888ytxx.shared.mvi.UiModel
import kotlinx.coroutines.flow.Flow

open class AndroidUiModelWrapper<STATE>(
    protected val uiModel: UiModel<STATE>
) : ViewModel(), UiModel<STATE> {
    override val state: Flow<STATE>
        get() = uiModel.state
    override val defaultValue: STATE
        get() = uiModel.defaultValue
    override fun onNewEvent(event: UiEvent) = uiModel.onNewEvent(event)
    override fun onCleared() {
        super.onCleared()
        (uiModel as? MultiplatformUiModel)?.onCleared()
    }
}