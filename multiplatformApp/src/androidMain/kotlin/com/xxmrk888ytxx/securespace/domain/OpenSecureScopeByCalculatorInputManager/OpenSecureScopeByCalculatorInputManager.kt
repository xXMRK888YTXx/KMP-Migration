package com.xxmrk888ytxx.securespace.domain.OpenSecureScopeByCalculatorInputManager

import com.xxmrk888ytxx.calculatorscreen.engine.CalculatorEventInterceptor
import javax.security.auth.callback.Callback

interface OpenSecureScopeByCalculatorInputManager {

    val calculatorInterceptor:CalculatorEventInterceptor

    fun registerCallback(openSecureScopeRequestCallBack:OpenSecureScopeRequestCallBack)

    fun removeCallBack(openSecureScopeRequestCallBack:OpenSecureScopeRequestCallBack)
}