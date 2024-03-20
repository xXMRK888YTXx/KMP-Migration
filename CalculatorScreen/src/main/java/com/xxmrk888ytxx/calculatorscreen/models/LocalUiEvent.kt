package com.xxmrk888ytxx.calculatorscreen.models

import com.xxmrk888ytxx.coreandroid.ShareInterfaces.MVI.UiEvent

internal sealed class LocalUiEvent : UiEvent {

    class CalculatorInput(val inputType: CalculatorInputType) : LocalUiEvent()
}