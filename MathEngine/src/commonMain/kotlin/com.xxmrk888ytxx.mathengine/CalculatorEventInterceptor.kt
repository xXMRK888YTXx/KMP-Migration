package com.xxmrk888ytxx.mathengine

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.xxmrk888ytxx.calculatorscreen.engine.MathEngine
import com.xxmrk888ytxx.calculatorscreen.engine.MathSymbol


interface CalculatorEventInterceptor {

    fun onResultUpdated(result: BigDecimal, engine: MathEngine)

    fun onInput(mathSymbol: MathSymbol, engine: MathEngine)

    fun onClear(engine: MathEngine)

    fun onInputSet(bigDecimal: BigDecimal,engine: MathEngine)
}