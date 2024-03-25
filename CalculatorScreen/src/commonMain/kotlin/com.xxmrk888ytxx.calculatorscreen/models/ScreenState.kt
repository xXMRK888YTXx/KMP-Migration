package com.xxmrk888ytxx.calculatorscreen.models

data class ScreenState(
    val calculatorInput:String = "",
    val mathResult: CalculatorResult = CalculatorResult.Stub
)