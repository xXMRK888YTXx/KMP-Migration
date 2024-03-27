package com.xxmrk888ytxx.securespace.DI.modules

import com.xxmrk888ytxx.calculatorscreen.CalculatorViewModel
import com.xxmrk888ytxx.calculatorscreen.contracts.MathEngineContract
import com.xxmrk888ytxx.calculatorscreen.engine.MathEngine
import com.xxmrk888ytxx.securespace.glue.calculatorScreen.MathEngineContractImpl
import dagger.Module
import dagger.Provides

@Module
interface CalculatorScreenModule {

    companion object {

        @Provides
        fun bindsMathEngineContract(mathEngine: MathEngine): MathEngineContract {
            return MathEngineContractImpl(mathEngine)
        }

        @Provides
        fun providesCalculatorViewModel(
            mathEngineContract: MathEngineContract
        ) : CalculatorViewModel = CalculatorViewModel(mathEngineContract)
    }
}