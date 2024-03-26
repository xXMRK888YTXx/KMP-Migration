package com.xxmrk888ytxx.securespace.data

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.xxmrk888ytxx.calculatorscreen.engine.MathEngine
import com.xxmrk888ytxx.calculatorscreen.engine.MathSymbol
import com.xxmrk888ytxx.mathengine.CalculatorEventInterceptor
import com.xxmrk888ytxx.securespace.domain.CalculatorPasswordManager
import com.xxmrk888ytxx.securespace.domain.OpenSecureScopeByCalculatorInputManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OpenSecureScopeByCalculatorInputManagerImpl constructor(
    private val calculatorPasswordManager: CalculatorPasswordManager,
    private val checkCalculatorScope:CoroutineScope
) : OpenSecureScopeByCalculatorInputManager {

    private val _openSecureSpaceEvent = MutableSharedFlow<Unit>()
    override val openSecureSpaceEvent: Flow<Unit> = _openSecureSpaceEvent.asSharedFlow()

    override val calculatorInterceptor: CalculatorEventInterceptor = object : CalculatorEventInterceptor {
        override fun onResultUpdated(result: BigDecimal, engine: MathEngine) {
        }

        override fun onInput(mathSymbol: MathSymbol, engine: MathEngine) {}

        override fun onClear(engine: MathEngine) {}

        override fun onInputSet(bigDecimal: BigDecimal, engine: MathEngine) {
            checkCalculatorScope.launch {
                if(calculatorPasswordManager.checkPassword(bigDecimal.toStringExpanded())) {
                    _openSecureSpaceEvent.emit(Unit)
                }
            }
        }

    }
}