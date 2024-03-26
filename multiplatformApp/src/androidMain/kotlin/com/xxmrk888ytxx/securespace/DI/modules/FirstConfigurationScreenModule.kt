package com.xxmrk888ytxx.securespace.DI.modules

import com.xxmrk888ytxx.firstconfigurationscreen.FirstConfigurationUiModel
import com.xxmrk888ytxx.firstconfigurationscreen.contracts.FinishConfigurationContract
import com.xxmrk888ytxx.firstconfigurationscreen.contracts.SetupCalculatorPasswordContract
import com.xxmrk888ytxx.firstconfigurationscreen.contracts.SetupSecureSpacePasswordContract
import com.xxmrk888ytxx.securespace.glue.firstConfigurationScreen.FinishConfigurationContractImpl
import com.xxmrk888ytxx.securespace.glue.firstConfigurationScreen.SetupCalculatorPasswordContractImpl
import com.xxmrk888ytxx.securespace.glue.firstConfigurationScreen.SetupSecureSpacePasswordContractImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface FirstConfigurationScreenModule {

    @Binds
    fun bindSetupSecureSpacePasswordContract(
        setupSecureSpacePasswordContractImpl: SetupSecureSpacePasswordContractImpl,
    ): SetupSecureSpacePasswordContract

    @Binds
    fun bindSetupCalculatorPasswordContract(
        setupCalculatorPasswordContractImpl: SetupCalculatorPasswordContractImpl,
    ): SetupCalculatorPasswordContract

    @Binds
    fun bindFinishConfigurationContract(
        finishConfigurationContractImpl: FinishConfigurationContractImpl,
    ): FinishConfigurationContract

    companion object {
        @Provides
        fun provideFirstConfigurationUiModel(
            setupCalculatorPasswordContract: SetupCalculatorPasswordContract,
            setupSecureSpacePasswordContract: SetupSecureSpacePasswordContract,
            finishConfigurationContract: FinishConfigurationContract,
        ): FirstConfigurationUiModel {
            return FirstConfigurationUiModel(
                setupCalculatorPasswordContract,
                setupSecureSpacePasswordContract,
                finishConfigurationContract
            )
        }
    }
}