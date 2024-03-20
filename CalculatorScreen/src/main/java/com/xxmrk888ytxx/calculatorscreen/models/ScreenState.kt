package com.xxmrk888ytxx.calculatorscreen.models

import com.xxmrk888ytxx.calculatorscreen.engine.MathResult

data class ScreenState(
    val calculatorInput:String = "",
    val mathResult: MathResult = MathResult.Stub
)