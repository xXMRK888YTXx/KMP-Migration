package com.xxmrk888ytxx.securespace.domain

import com.xxmrk888ytxx.mathengine.CalculatorEventInterceptor
import kotlinx.coroutines.flow.Flow

interface OpenSecureScopeByCalculatorInputManager {

    val calculatorInterceptor: CalculatorEventInterceptor

    val openSecureSpaceEvent: Flow<Unit>
}