package com.xxmrk888ytxx.calculatorscreen.models

sealed interface CalculatorResult {
    data object Stub : CalculatorResult
    data class Result(val resultString: String) : CalculatorResult
    data class Error(val errorString: String) : CalculatorResult
}