package com.xxmrk888ytxx.calculatorscreen.engine

import com.ionspin.kotlin.bignum.decimal.BigDecimal

sealed class MathResult {

    data class Result(val number: BigDecimal) : MathResult()

    data class Error(val exception: MathException) : MathResult()

    data object Stub : MathResult()
}