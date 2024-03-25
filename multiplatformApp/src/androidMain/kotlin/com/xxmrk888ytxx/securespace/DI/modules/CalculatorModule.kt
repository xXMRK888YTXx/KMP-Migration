package com.xxmrk888ytxx.securespace.DI.modules

import com.xxmrk888ytxx.calculatorscreen.engine.DefaultMathEngine
import com.xxmrk888ytxx.calculatorscreen.engine.MathEngine
import com.xxmrk888ytxx.securespace.DI.qualifiers.CalculatorScopeQualifier
import com.xxmrk888ytxx.securespace.domain.OpenSecureScopeByCalculatorInputManager.OpenSecureScopeByCalculatorInputManager
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope

@Module
class CalculatorModule {
    @Provides
    fun providesCalculatorEngine(
        @CalculatorScopeQualifier calculationScope: CoroutineScope,
        openSecureScopeByCalculatorInputManager: OpenSecureScopeByCalculatorInputManager,
    ): MathEngine {
        return DefaultMathEngine(
            calculationScope,
            setOf(openSecureScopeByCalculatorInputManager.calculatorInterceptor)
        )
    }
}