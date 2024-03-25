package com.xxmrk888ytxx.securespace.DI.modules

import com.xxmrk888ytxx.calculatorscreen.CalculatorViewModel
import com.xxmrk888ytxx.calculatorscreen.contracts.MathEngineContract
import com.xxmrk888ytxx.securespace.glue.CalculatorScreen.MathEngineContractImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface CalculatorScreenModule {
    @Binds
    fun bindsMathEngineContract(mathEngineContractImpl: MathEngineContractImpl): MathEngineContract

    companion object {
        @Provides
        fun providesCalculatorViewModel(
            mathEngineContract: MathEngineContract
        ) : CalculatorViewModel = CalculatorViewModel(mathEngineContract)
    }
}