package com.xxmrk888ytxx.calculatorscreen.engine

import com.ionspin.kotlin.bignum.decimal.BigDecimal


interface CalculatorEventInterceptor {

    fun onResultUpdated(result: BigDecimal, engine: MathEngine)

    fun onInput(mathSymbol: MathSymbol,engine: MathEngine)

    fun onClear(engine: MathEngine)

    fun onInputSet(bigDecimal: BigDecimal,engine: MathEngine)
}