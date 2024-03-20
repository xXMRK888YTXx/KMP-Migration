package com.xxmrk888ytxx.calculatorscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxmrk888ytxx.calculatorscreen.contracts.ProvideCalculationEngine
import com.xxmrk888ytxx.calculatorscreen.engine.DefaultMathEngine
import com.xxmrk888ytxx.calculatorscreen.engine.MathResult
import com.xxmrk888ytxx.calculatorscreen.engine.MathSymbol
import com.xxmrk888ytxx.calculatorscreen.models.CalculatorInputType
import com.xxmrk888ytxx.calculatorscreen.models.LocalUiEvent
import com.xxmrk888ytxx.calculatorscreen.models.ScreenState
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.MVI.UiEvent
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.MVI.UiModel
import com.xxmrk888ytxx.coreandroid.getWithCast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class CalculatorViewModel @Inject constructor(
    private val provideCalculationEngine: ProvideCalculationEngine
) : ViewModel(), UiModel<ScreenState> {


    @OptIn(ExperimentalCoroutinesApi::class)
    private val calculationScope = CoroutineScope(
        SupervisorJob() + Dispatchers.Default
    )

    private val engine = provideCalculationEngine.provideEngine(calculationScope)

    override fun handleEvent(event: UiEvent) {
        if (event !is LocalUiEvent) return

        when (event) {
            is LocalUiEvent.CalculatorInput -> handleCalculatorInput(event.inputType)
        }
    }


    override val state: Flow<ScreenState> = combine(
        engine.inputtedSymbols.map { list -> list.joinToString(separator = "") { it.stringSymbol } },
        engine.result
    ) { flowArray ->
        ScreenState(
            calculatorInput = flowArray.getWithCast(0),
            mathResult = flowArray.getWithCast(1)
        ).also { cashedValue = it }
    }

    private var cashedValue: ScreenState = ScreenState()

    override val defValue: ScreenState
        get() = cashedValue

    private fun handleCalculatorInput(inputType: CalculatorInputType) = viewModelScope.launch() {


        when (inputType) {

            CalculatorInputType.CLEAR -> {
                engine.clear()
            }

            CalculatorInputType.EXPONENTIATION -> engine.input(MathSymbol.EXPONENTIATION)

            CalculatorInputType.PI -> engine.input(MathSymbol.PI)

            CalculatorInputType.DIVISION -> engine.input(MathSymbol.DIVISION)

            CalculatorInputType.MULTIPLICATION -> engine.input(MathSymbol.MULTIPLICATION)

            CalculatorInputType.MINUS -> engine.input(MathSymbol.MINUS)

            CalculatorInputType.PLUS -> engine.input(MathSymbol.PLUS)

            CalculatorInputType.EQUALS -> {
                val result = engine.result.first()

                if(result !is MathResult.Result) return@launch

                engine.setInput(result.number)
            }

            CalculatorInputType.REMOVE_SYMBOL -> engine.removeLast()

            CalculatorInputType.POINT -> engine.input(MathSymbol.POINT)

            //
            CalculatorInputType.ZERO -> engine.input(MathSymbol.NUMBER(0))

            CalculatorInputType.ONE -> engine.input(MathSymbol.NUMBER(1))

            CalculatorInputType.TWO -> engine.input(MathSymbol.NUMBER(2))

            CalculatorInputType.THREE -> engine.input(MathSymbol.NUMBER(3))

            CalculatorInputType.FOUR -> engine.input(MathSymbol.NUMBER(4))

            CalculatorInputType.FIVE -> engine.input(MathSymbol.NUMBER(5))

            CalculatorInputType.SIX -> engine.input(MathSymbol.NUMBER(6))

            CalculatorInputType.SEVEN -> engine.input(MathSymbol.NUMBER(7))

            CalculatorInputType.EIGHT -> engine.input(MathSymbol.NUMBER(8))

            CalculatorInputType.NINE -> engine.input(MathSymbol.NUMBER(9))
        }
    }

}