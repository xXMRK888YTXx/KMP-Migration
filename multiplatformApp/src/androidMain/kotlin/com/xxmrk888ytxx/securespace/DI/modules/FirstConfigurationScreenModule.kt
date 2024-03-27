package com.xxmrk888ytxx.securespace.DI.modules

import com.xxmrk888ytxx.firstconfigurationscreen.FirstConfigurationUiModel
import com.xxmrk888ytxx.firstconfigurationscreen.contracts.FinishConfigurationContract
import com.xxmrk888ytxx.firstconfigurationscreen.contracts.SetupCalculatorPasswordContract
import com.xxmrk888ytxx.firstconfigurationscreen.contracts.SetupSecureSpacePasswordContract
import com.xxmrk888ytxx.securespace.domain.CalculatorPasswordManager
import com.xxmrk888ytxx.securespace.domain.FirstStartStateHolder
import com.xxmrk888ytxx.securespace.domain.useCase.CreateSecureSpaceUseCase
import com.xxmrk888ytxx.securespace.glue.firstConfigurationScreen.FinishConfigurationContractImpl
import com.xxmrk888ytxx.securespace.glue.firstConfigurationScreen.SetupCalculatorPasswordContractImpl
import com.xxmrk888ytxx.securespace.glue.firstConfigurationScreen.SetupSecureSpacePasswordContractImpl
import dagger.Module
import dagger.Provides

@Module
interface FirstConfigurationScreenModule {
    companion object {
        @Provides
        fun bindSetupSecureSpacePasswordContract(
            createSecureSpaceUseCase: CreateSecureSpaceUseCase,
        ): SetupSecureSpacePasswordContract {
            return SetupSecureSpacePasswordContractImpl(createSecureSpaceUseCase)
        }

        @Provides
        fun bindSetupCalculatorPasswordContract(
            calculatorPasswordManager: CalculatorPasswordManager,
        ): SetupCalculatorPasswordContract =
            SetupCalculatorPasswordContractImpl(calculatorPasswordManager)

        @Provides
        fun bindFinishConfigurationContract(
            firstStartStateHolder: FirstStartStateHolder
        ): FinishConfigurationContract {
            return FinishConfigurationContractImpl(firstStartStateHolder)
        }

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