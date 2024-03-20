package com.xxmrk888ytxx.calculatorscreen.engine

import android.icu.math.BigDecimal


interface MathAction {

    suspend fun onAction(numbers:List<BigDecimal>) : BigDecimal
}
