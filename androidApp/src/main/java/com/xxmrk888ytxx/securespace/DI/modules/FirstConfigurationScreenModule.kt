package com.xxmrk888ytxx.securespace.DI.modules

import com.xxmrk888ytxx.firstconfigurationscreen.contracts.FinishConfigurationContract
import com.xxmrk888ytxx.firstconfigurationscreen.contracts.SetupCalculatorPasswordContract
import com.xxmrk888ytxx.firstconfigurationscreen.contracts.SetupSecureSpacePasswordContract
import com.xxmrk888ytxx.securespace.glue.FirstConfigurationScreen.FinishConfigurationContractImpl
import com.xxmrk888ytxx.securespace.glue.FirstConfigurationScreen.SetupCalculatorPasswordContractImpl
import com.xxmrk888ytxx.securespace.glue.FirstConfigurationScreen.SetupSecureSpacePasswordContractImpl
import dagger.Binds
import dagger.Module

@Module
interface FirstConfigurationScreenModule {

    @Binds
    fun bindSetupSecureSpacePasswordContract(
        setupSecureSpacePasswordContractImpl:SetupSecureSpacePasswordContractImpl
    ) : SetupSecureSpacePasswordContract

    @Binds
    fun bindSetupCalculatorPasswordContract(
        setupCalculatorPasswordContractImpl: SetupCalculatorPasswordContractImpl
    ) : SetupCalculatorPasswordContract

    @Binds
    fun bindFinishConfigurationContract(
        finishConfigurationContractImpl: FinishConfigurationContractImpl
    ) : FinishConfigurationContract
}