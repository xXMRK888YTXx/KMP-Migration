package com.xxmrk888ytxx.securespace.domain.OpenSecureScopeByCalculatorInputManager

import android.icu.math.BigDecimal
import com.xxmrk888ytxx.androidcore.fastDebugLog
import com.xxmrk888ytxx.calculatorscreen.engine.CalculatorEventInterceptor
import com.xxmrk888ytxx.calculatorscreen.engine.MathEngine
import com.xxmrk888ytxx.calculatorscreen.engine.MathSymbol
import com.xxmrk888ytxx.securespace.DI.qualifiers.CheckCalculatorScope
import com.xxmrk888ytxx.securespace.domain.CalculatorPasswordManager.CalculatorPasswordManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OpenSecureScopeByCalculatorInputManagerImpl @Inject constructor(
    private val calculatorPasswordManager: CalculatorPasswordManager,
    @CheckCalculatorScope private val checkCalculatorScope:CoroutineScope
) : OpenSecureScopeByCalculatorInputManager {

    private val callbacks:MutableSet<OpenSecureScopeRequestCallBack> = mutableSetOf()

    override val calculatorInterceptor: CalculatorEventInterceptor = object : CalculatorEventInterceptor {
        override fun onResultUpdated(result: BigDecimal, engine: MathEngine) {
        }

        override fun onInput(mathSymbol: MathSymbol, engine: MathEngine) {}

        override fun onClear(engine: MathEngine) {}

        override fun onInputSet(bigDecimal: BigDecimal, engine: MathEngine) {
            checkCalculatorScope.launch {
                if(calculatorPasswordManager.checkPassword(bigDecimal.toString())) {

                    withContext(Dispatchers.Main) {
                        val localCallBacks = callbacks

                        localCallBacks.forEach {
                            it.onRequest()
                        }

                        engine.clear()
                    }
                }
            }
        }

    }

    override fun registerCallback(openSecureScopeRequestCallBack: OpenSecureScopeRequestCallBack) {
        callbacks.add(openSecureScopeRequestCallBack)
    }

    override fun removeCallBack(openSecureScopeRequestCallBack: OpenSecureScopeRequestCallBack) {
        callbacks.add(openSecureScopeRequestCallBack)
    }
}