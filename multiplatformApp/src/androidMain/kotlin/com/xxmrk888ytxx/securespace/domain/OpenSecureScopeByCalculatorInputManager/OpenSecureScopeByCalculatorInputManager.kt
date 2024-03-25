package com.xxmrk888ytxx.securespace.domain.OpenSecureScopeByCalculatorInputManager

import com.xxmrk888ytxx.calculatorscreen.engine.CalculatorEventInterceptor
import kotlinx.coroutines.flow.Flow

interface OpenSecureScopeByCalculatorInputManager {

    val calculatorInterceptor:CalculatorEventInterceptor

    val openSecureSpaceEvent: Flow<Unit>
}