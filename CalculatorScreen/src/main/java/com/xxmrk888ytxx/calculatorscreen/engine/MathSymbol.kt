package com.xxmrk888ytxx.calculatorscreen.engine

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.DecimalMode
import com.ionspin.kotlin.bignum.decimal.RoundingMode
import com.xxmrk888ytxx.calculatorscreen.exceptions.DivineByZeroException
import com.xxmrk888ytxx.calculatorscreen.exceptions.MathActionCalculationException

private val defaultDecimalMode =
    DecimalMode(decimalPrecision = 20, roundingMode = RoundingMode.ROUND_HALF_FLOOR)


sealed class MathSymbol(
    val stringSymbol: String,
) {
    data class NUMBER(val number: Int) : MathSymbol(number.toString()) {
        init {
            if (number !in 0..9) error("The number must be in the range from 0 to 9")
        }
    }

    data object EXPONENTIATION : MathSymbol("^"), MathAction {
        override suspend fun onAction(numbers: List<BigDecimal>): BigDecimal {
            val n1 = numbers.getOrNull(0)
            val n2 = numbers.getOrNull(1)

            if (n1 == null || n2 == null) throw MathActionCalculationException("n1 or n2 numbers are null")

            val f = n2.toStringExpanded().takeWhile { it != '.' }.toLong()
            return n1.pow(f)
        }


    }


    data object DIVISION : MathSymbol("÷"), MathAction {
        override suspend fun onAction(numbers: List<BigDecimal>): BigDecimal {
            val n1 = numbers.getOrNull(0)
            val n2 = numbers.getOrNull(1)

            if (n1 == null || n2 == null) throw MathActionCalculationException("n1 or n2 numbers are null")
            if (n2 == BigDecimal.ZERO) throw DivineByZeroException()


            return n1.divide(n2, defaultDecimalMode)
        }
    }

    data object MULTIPLICATION : MathSymbol("×"), MathAction {
        override suspend fun onAction(numbers: List<BigDecimal>): BigDecimal {
            val n1 = numbers.getOrNull(0)
            val n2 = numbers.getOrNull(1)

            if (n1 == null || n2 == null) throw MathActionCalculationException("n1 or n2 numbers are null")

            return n1.multiply(n2, defaultDecimalMode)
        }
    }

    data object MINUS : MathSymbol("-"), MathAction {
        override suspend fun onAction(numbers: List<BigDecimal>): BigDecimal {
            val n1 = numbers.getOrNull(0)
            val n2 = numbers.getOrNull(1)

            if (n1 == null || n2 == null) throw MathActionCalculationException("n1 or n2 numbers are null")

            return n1.subtract(n2, defaultDecimalMode)
        }
    }

    data object PLUS : MathSymbol("+"), MathAction {
        override suspend fun onAction(numbers: List<BigDecimal>): BigDecimal {
            val n1 = numbers.getOrNull(0)
            val n2 = numbers.getOrNull(1)

            if (n1 == null || n2 == null) throw MathActionCalculationException("n1 or n2 numbers are null")

            return n1.add(n2, defaultDecimalMode)
        }
    }

    data object POINT : MathSymbol(".")

    data object PI : MathSymbol("π")

    companion object {
        val HIGH_PRIORITY_MATH_SYMBOLS = setOf<MathAction>(EXPONENTIATION, DIVISION, MULTIPLICATION)
    }


}