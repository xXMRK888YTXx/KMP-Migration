package com.xxmrk888ytxx.calculatorscreen.contracts

import com.xxmrk888ytxx.calculatorscreen.models.CalculatorInputType
import com.xxmrk888ytxx.calculatorscreen.models.CalculatorResult
import kotlinx.coroutines.flow.Flow

interface MathEngineContract {
    val input: Flow<String>
    val result: Flow<CalculatorResult>
    suspend fun onInput(calculatorInputType: CalculatorInputType)
}