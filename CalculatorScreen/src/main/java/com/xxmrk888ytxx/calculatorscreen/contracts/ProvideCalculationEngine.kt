package com.xxmrk888ytxx.calculatorscreen.contracts

import com.xxmrk888ytxx.calculatorscreen.engine.MathEngine
import kotlinx.coroutines.CoroutineScope

interface ProvideCalculationEngine {

    fun provideEngine(calculationScope:CoroutineScope) : MathEngine
}