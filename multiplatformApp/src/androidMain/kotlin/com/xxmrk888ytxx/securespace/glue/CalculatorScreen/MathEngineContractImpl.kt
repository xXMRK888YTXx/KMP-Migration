package com.xxmrk888ytxx.securespace.glue.CalculatorScreen

import android.content.Context
import com.xxmrk888ytxx.calculatorscreen.contracts.MathEngineContract
import com.xxmrk888ytxx.calculatorscreen.engine.MathEngine
import com.xxmrk888ytxx.calculatorscreen.engine.MathResult
import com.xxmrk888ytxx.calculatorscreen.engine.MathSymbol
import com.xxmrk888ytxx.calculatorscreen.models.CalculatorInputType
import com.xxmrk888ytxx.calculatorscreen.models.CalculatorResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MathEngineContractImpl @Inject constructor(
    private val mathEngine: MathEngine,
    private val context: Context,
) : MathEngineContract {
    override val input: Flow<String> =
        mathEngine.inputtedSymbols.map { list -> list.joinToString("") { it.stringSymbol } }

    override val result: Flow<CalculatorResult> = mathEngine.result.map {
        when(it) {
            is MathResult.Error -> {

                /**
                 * val errorMessage = when (it.exception) {
                 *                     is DivineByZeroException -> context.getString(R.string.can_t_divide_by_0)
                 *
                 *                     is AnswerTooLargeException -> context.getString(R.string.answer_is_too_large)
                 *
                 *                     else -> context.getString(R.string.unknown_error)
                 *                 }
                 *
                 * */

                CalculatorResult.Error("errorMessage")
            }
            is MathResult.Result -> CalculatorResult.Result(it.number.toStringExpanded())
            MathResult.Stub -> CalculatorResult.Stub
        }
    }

    override suspend fun onInput(calculatorInputType: CalculatorInputType) {
        when (calculatorInputType) {

            CalculatorInputType.CLEAR -> {
                mathEngine.clear()
            }

            CalculatorInputType.EXPONENTIATION -> mathEngine.input(MathSymbol.EXPONENTIATION)

            CalculatorInputType.PI -> mathEngine.input(MathSymbol.PI)

            CalculatorInputType.DIVISION -> mathEngine.input(MathSymbol.DIVISION)

            CalculatorInputType.MULTIPLICATION -> mathEngine.input(MathSymbol.MULTIPLICATION)

            CalculatorInputType.MINUS -> mathEngine.input(MathSymbol.MINUS)

            CalculatorInputType.PLUS -> mathEngine.input(MathSymbol.PLUS)

            CalculatorInputType.EQUALS -> {
                val result = mathEngine.result.first()

                if(result !is MathResult.Result) return

                mathEngine.setInput(result.number)
            }

            CalculatorInputType.REMOVE_SYMBOL -> mathEngine.removeLast()

            CalculatorInputType.POINT -> mathEngine.input(MathSymbol.POINT)

            //
            CalculatorInputType.ZERO -> mathEngine.input(MathSymbol.NUMBER(0))

            CalculatorInputType.ONE -> mathEngine.input(MathSymbol.NUMBER(1))

            CalculatorInputType.TWO -> mathEngine.input(MathSymbol.NUMBER(2))

            CalculatorInputType.THREE -> mathEngine.input(MathSymbol.NUMBER(3))

            CalculatorInputType.FOUR -> mathEngine.input(MathSymbol.NUMBER(4))

            CalculatorInputType.FIVE -> mathEngine.input(MathSymbol.NUMBER(5))

            CalculatorInputType.SIX -> mathEngine.input(MathSymbol.NUMBER(6))

            CalculatorInputType.SEVEN -> mathEngine.input(MathSymbol.NUMBER(7))

            CalculatorInputType.EIGHT -> mathEngine.input(MathSymbol.NUMBER(8))

            CalculatorInputType.NINE -> mathEngine.input(MathSymbol.NUMBER(9))
        }
    }
}