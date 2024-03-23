package com.xxmrk888ytxx.shared.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class MultiplatformViewModel<STATE> : ViewModel(
    CoroutineScope(Dispatchers.Unconfined + SupervisorJob())
),UiModel<STATE>