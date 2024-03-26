package com.xxmrk888ytxx.calculatorscreen.models

import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource


sealed interface CalculatorResult {
    data object Stub : CalculatorResult
    data class Result(val resultString: String) : CalculatorResult
    data class Error @OptIn(ExperimentalResourceApi::class) constructor(val errorString: StringResource) : CalculatorResult
}