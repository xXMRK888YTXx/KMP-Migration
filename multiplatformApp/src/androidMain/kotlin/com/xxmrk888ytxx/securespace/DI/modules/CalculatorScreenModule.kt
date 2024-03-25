package com.xxmrk888ytxx.securespace.DI.modules

import com.xxmrk888ytxx.calculatorscreen.contracts.MathEngineContract
import com.xxmrk888ytxx.securespace.glue.CalculatorScreen.MathEngineContractImpl
import dagger.Binds
import dagger.Module

@Module
interface CalculatorScreenModule {
    @Binds
    fun binds(mathEngineContractImpl: MathEngineContractImpl): MathEngineContract
}