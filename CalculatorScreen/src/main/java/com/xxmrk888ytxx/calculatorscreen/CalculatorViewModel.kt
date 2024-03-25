package com.xxmrk888ytxx.calculatorscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxmrk888ytxx.calculatorscreen.contracts.MathEngineContract
import com.xxmrk888ytxx.calculatorscreen.models.LocalUiEvent
import com.xxmrk888ytxx.calculatorscreen.models.ScreenState
import com.xxmrk888ytxx.shared.getWithCast
import com.xxmrk888ytxx.shared.mvi.UiEvent
import com.xxmrk888ytxx.shared.mvi.UiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

class CalculatorViewModel @Inject constructor(
    private val mathEngineContract: MathEngineContract,
) : ViewModel(), UiModel<ScreenState> {

    override fun onNewEvent(event: UiEvent) {
        if (event !is LocalUiEvent) return

        when (event) {
            is LocalUiEvent.CalculatorInput -> viewModelScope.launch {
                mathEngineContract.onInput(event.inputType)
            }
        }
    }


    override val state: Flow<ScreenState> = combine(
        mathEngineContract.input,
        mathEngineContract.result
    ) { flowArray ->
        ScreenState(
            calculatorInput = flowArray.getWithCast(0),
            mathResult = flowArray.getWithCast(1)
        ).also { cashedValue = it }
    }

    private var cashedValue: ScreenState = ScreenState()

    override val defaultValue: ScreenState
        get() = cashedValue

}