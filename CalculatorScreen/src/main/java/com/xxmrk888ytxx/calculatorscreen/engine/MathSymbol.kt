package com.xxmrk888ytxx.calculatorscreen.engine

import android.icu.math.BigDecimal
import com.xxmrk888ytxx.androidcore.fastDebugLog
import com.xxmrk888ytxx.calculatorscreen.engine.DefaultMathEngine.Companion.MAX_ANSWER_NUMBERS_COUNT
import com.xxmrk888ytxx.calculatorscreen.exceptions.DivineByZeroException
import com.xxmrk888ytxx.calculatorscreen.exceptions.MathActionCalculationException

sealed class MathSymbol(val stringSymbol:String) {

    data class NUMBER(val number:Int) : MathSymbol(number.toString()) {
        init {
            if(number !in 0..9) error("The number must be in the range from 0 to 9")
        }
    }

    data object EXPONENTIATION : MathSymbol("^"),MathAction {
        override suspend fun onAction(numbers: List<BigDecimal>): BigDecimal {
            val n1 = numbers.getOrNull(0)
            val n2 = numbers.getOrNull(1)

            if(n1 == null || n2 == null) throw MathActionCalculationException("n1 or n2 numbers are null")

            return n1.pow(n2)
        }


    }


    data object DIVISION : MathSymbol("÷"),MathAction {
        override suspend fun onAction(numbers: List<BigDecimal>): BigDecimal {
            val n1 = numbers.getOrNull(0)
            val n2 = numbers.getOrNull(1)

            if(n1 == null || n2 == null) throw MathActionCalculationException("n1 or n2 numbers are null")
            if(n2 == BigDecimal.ZERO) throw DivineByZeroException()


            return BigDecimal(n1.toBigDecimal().divide(n2.toBigDecimal()))
        }
    }

    data object MULTIPLICATION : MathSymbol("×"),MathAction {
        override suspend fun onAction(numbers: List<BigDecimal>): BigDecimal {
            val n1 = numbers.getOrNull(0)
            val n2 = numbers.getOrNull(1)

            if(n1 == null || n2 == null) throw MathActionCalculationException("n1 or n2 numbers are null")

            return n1.multiply(n2)
        }
    }

    data object MINUS : MathSymbol("-"),MathAction {
        override suspend fun onAction(numbers: List<BigDecimal>): BigDecimal {
            val n1 = numbers.getOrNull(0)
            val n2 = numbers.getOrNull(1)

            if(n1 == null || n2 == null) throw MathActionCalculationException("n1 or n2 numbers are null")

            return n1.subtract(n2)
        }
    }

    data object PLUS : MathSymbol("+"),MathAction {
        override suspend fun onAction(numbers: List<BigDecimal>): BigDecimal {
            val n1 = numbers.getOrNull(0)
            val n2 = numbers.getOrNull(1)

            if(n1 == null || n2 == null) throw MathActionCalculationException("n1 or n2 numbers are null")

            return n1.add(n2)
        }
    }

    data object POINT : MathSymbol(".")

    data object PI : MathSymbol("π")

    companion object {
         val HIGH_PRIORITY_MATH_SYMBOLS = setOf<MathAction>(EXPONENTIATION,DIVISION,MULTIPLICATION)
    }


}