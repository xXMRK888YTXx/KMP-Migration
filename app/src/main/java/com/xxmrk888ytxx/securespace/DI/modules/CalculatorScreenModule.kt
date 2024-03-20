package com.xxmrk888ytxx.securespace.DI.modules

import com.xxmrk888ytxx.calculatorscreen.contracts.ProvideCalculationEngine
import com.xxmrk888ytxx.securespace.glue.CalculatorScreen.ProvideCalculationEngineImpl
import dagger.Binds
import dagger.Module

@Module
interface CalculatorScreenModule {

    @Binds
    fun bindProvideCalculationEngine(
        provideCalculationEngineImpl:ProvideCalculationEngineImpl
    ) : ProvideCalculationEngine
}