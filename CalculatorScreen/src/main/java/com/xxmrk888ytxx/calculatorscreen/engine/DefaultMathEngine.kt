package com.xxmrk888ytxx.calculatorscreen.engine

import android.icu.math.BigDecimal
import com.xxmrk888ytxx.calculatorscreen.exceptions.InputException
import com.xxmrk888ytxx.calculatorscreen.exceptions.AnswerTooLargeException
import com.xxmrk888ytxx.coreandroid.launchAndCancelChildren
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import java.lang.ArithmeticException

class DefaultMathEngine(
    private val calculationScope:CoroutineScope,
    private val interceptors: Set<CalculatorEventInterceptor> = setOf()
) : MathEngine {


    private val _inputtedSymbols: MutableStateFlow<List<MathSymbol>> = MutableStateFlow(
        emptyList()
    )

    override val inputtedSymbols = _inputtedSymbols.asStateFlow()

    private val _result = MutableStateFlow<MathResult>(MathResult.Stub)

    override val result = _result.asStateFlow()

    private var isFloatNumber = false


    override suspend fun input(mathSymbol: MathSymbol) {
        val list = inputtedSymbols.value

        if (
            ((list.isEmpty() || list.lastOrNull() is MathAction) && (mathSymbol is MathAction || mathSymbol is MathSymbol.POINT)) ||
            ((list.lastOrNull() is MathSymbol.POINT && mathSymbol is MathSymbol.POINT) || (mathSymbol is MathSymbol.POINT && isFloatNumber)) ||
            ((list.lastOrNull() is MathSymbol.PI && mathSymbol !is MathAction) || (list.lastOrNull() !is MathAction && mathSymbol is MathSymbol.PI))
        ) return

        if (mathSymbol is MathSymbol.POINT) isFloatNumber = true

        if (mathSymbol is MathAction) isFloatNumber = false

        _inputtedSymbols.update { list.toMutableList().apply { add(mathSymbol) } }

        interceptors.forEach {
            it.onInput(mathSymbol,this)
        }

        updateResult()
    }

    override suspend fun setInput(number: BigDecimal) {
        clear()
        val outputList = mutableListOf<MathSymbol>()

        number.toString().forEach {
            val numberType = when (it) {
                '0' -> MathSymbol.NUMBER(0)
                '1' -> MathSymbol.NUMBER(1)
                '2' -> MathSymbol.NUMBER(2)
                '3' -> MathSymbol.NUMBER(3)
                '4' -> MathSymbol.NUMBER(4)
                '5' -> MathSymbol.NUMBER(5)
                '6' -> MathSymbol.NUMBER(6)
                '7' -> MathSymbol.NUMBER(7)
                '8' -> MathSymbol.NUMBER(8)
                '9' -> MathSymbol.NUMBER(9)
                '.' -> MathSymbol.POINT
                '-' -> MathSymbol.MINUS
                else -> error("")
            }

            outputList.add(numberType)

        }

        _inputtedSymbols.update { outputList }

        interceptors.forEach {
            it.onInputSet(number,this)
        }

        updateResult()
    }

    override  suspend fun removeLast()  {
        val list = inputtedSymbols.value

        if (list.isEmpty()) return

        if (list.lastOrNull() is MathSymbol.POINT) isFloatNumber = false

        _inputtedSymbols.update { list.toMutableList().dropLast(1) }

        updateResult()
    }

    override suspend fun clear()  {
        calculationScope.coroutineContext.cancelChildren()
        _inputtedSymbols.update { emptyList() }
        isFloatNumber = false
        _result.update { MathResult.Stub }

        interceptors.forEach {
            it.onClear(this)
        }
    }


    private fun updateResult() = calculationScope.launchAndCancelChildren {
        try {
            val resultDeque = ArrayDeque<CalculationObject>()
            val symbols = _inputtedSymbols.value
            var numberString = ""

            suspend fun addNumberInResultDeque() {
                if (numberString.isEmpty()) return

                resultDeque.add(CalculationObject.Number(BigDecimal(numberString)))
                numberString = ""
            }

            suspend fun findHighPriorityMathActionPos(): Int? {
                resultDeque.forEachIndexed { index, it ->
                    if (it is CalculationObject.Action && MathSymbol.HIGH_PRIORITY_MATH_SYMBOLS.contains(
                            it.action
                        )
                    ) return index
                }

                return null
            }

            suspend fun findMathActionPos(): Int? {
                resultDeque.forEachIndexed { index, it ->
                    if (it is CalculationObject.Action) return index
                }

                return null
            }


            symbols
                .let {
                    if(it.firstOrNull() == MathSymbol.MINUS) {
                        numberString = "-"
                        it.drop(1)
                    } else it
                }
                .forEach {
                when (it) {
                    is MathAction -> {
                        addNumberInResultDeque()
                        resultDeque.add(CalculationObject.Action(it))
                    }

                    is MathSymbol.PI -> {
                        resultDeque.add(CalculationObject.Number(BigDecimal(Math.PI)))
                    }

                    else -> {
                        numberString += it.stringSymbol
                    }
                }
            }

            addNumberInResultDeque()


            if (!isValidInput(resultDeque)) throw InputException()

            //
            var highPrioritySymbolPos = findHighPriorityMathActionPos()

            while (highPrioritySymbolPos != null && isActive) {
                val action = resultDeque[highPrioritySymbolPos] as CalculationObject.Action
                val n1 = resultDeque[highPrioritySymbolPos - 1] as CalculationObject.Number
                val n2 = resultDeque[highPrioritySymbolPos + 1] as CalculationObject.Number

                val result = action.action.onAction(listOf(n1.num, n2.num))

                resultDeque[highPrioritySymbolPos - 1] = CalculationObject.Number(result)

                resultDeque.removeAt(highPrioritySymbolPos)
                resultDeque.removeAt(highPrioritySymbolPos)


                highPrioritySymbolPos = findHighPriorityMathActionPos()
            }

            //

            //
            var mathActionPos = findMathActionPos()

            while (mathActionPos != null && isActive) {
                val action = resultDeque[mathActionPos] as CalculationObject.Action
                val n1 = resultDeque[mathActionPos - 1] as CalculationObject.Number
                val n2 = resultDeque[mathActionPos + 1] as CalculationObject.Number

                val result = action.action.onAction(listOf(n1.num, n2.num))

                resultDeque[mathActionPos - 1] = CalculationObject.Number(result)

                resultDeque.removeAt(mathActionPos)
                resultDeque.removeAt(mathActionPos)


                mathActionPos = findHighPriorityMathActionPos()
            }

            //

            val calculationResult = (resultDeque.first() as CalculationObject.Number).num

            if (calculationResult.toString().length >= MAX_ANSWER_NUMBERS_COUNT) throw AnswerTooLargeException()

            _result.update { MathResult.Result(calculationResult) }

            interceptors.forEach {
                it.onResultUpdated(calculationResult,this@DefaultMathEngine)
            }
        } catch (e: MathException) {
            if (e is InputException) return@launchAndCancelChildren

            _result.update { MathResult.Error(e) }
        } catch (e: ArithmeticException) {
            _result.update { MathResult.Error(AnswerTooLargeException()) }
        }
    }

    private suspend fun isValidInput(symbols: List<CalculationObject>): Boolean {
        var mathActionCount = 0
        var numbersCount = 0

        symbols.forEach {
            if (it is CalculationObject.Action) mathActionCount += 1
            else numbersCount += 1
        }

        return mathActionCount == numbersCount - 1
    }

    private sealed class CalculationObject {

        class Number(val num: BigDecimal) : CalculationObject()

        class Action(val action: MathAction) : CalculationObject()
    }


    companion object {
         const val MAX_ANSWER_NUMBERS_COUNT = 2000
    }


}