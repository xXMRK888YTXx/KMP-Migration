package com.xxmrk888ytxx.securespace.glue.CalculatorScreen

import com.xxmrk888ytxx.calculatorscreen.contracts.ProvideCalculationEngine
import com.xxmrk888ytxx.calculatorscreen.engine.DefaultMathEngine
import com.xxmrk888ytxx.calculatorscreen.engine.MathEngine
import com.xxmrk888ytxx.securespace.domain.OpenSecureScopeByCalculatorInputManager.OpenSecureScopeByCalculatorInputManager
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class ProvideCalculationEngineImpl @Inject constructor(
    private val openSecureScopeByCalculatorInputManager: OpenSecureScopeByCalculatorInputManager
) : ProvideCalculationEngine {

    override fun provideEngine(calculationScope: CoroutineScope): MathEngine {
        return DefaultMathEngine(calculationScope, setOf(openSecureScopeByCalculatorInputManager.calculatorInterceptor))
    }


}