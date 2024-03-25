package com.xxmrk888ytxx.calculatorscreen.engine

import com.ionspin.kotlin.bignum.decimal.BigDecimal


interface MathAction {

    suspend fun onAction(numbers:List<BigDecimal>) : BigDecimal
}
