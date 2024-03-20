package com.xxmrk888ytxx.calculatorscreen.engine

import android.icu.math.BigDecimal


interface CalculatorEventInterceptor {

    fun onResultUpdated(result: BigDecimal, engine: MathEngine)

    fun onInput(mathSymbol: MathSymbol,engine: MathEngine)

    fun onClear(engine: MathEngine)

    fun onInputSet(bigDecimal: BigDecimal,engine: MathEngine)
}