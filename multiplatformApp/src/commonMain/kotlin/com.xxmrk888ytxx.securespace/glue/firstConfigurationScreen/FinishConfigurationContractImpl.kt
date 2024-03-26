package com.xxmrk888ytxx.securespace.glue.firstConfigurationScreen

import com.xxmrk888ytxx.firstconfigurationscreen.contracts.FinishConfigurationContract
import com.xxmrk888ytxx.securespace.domain.FirstStartStateHolder
import com.xxmrk888ytxx.shared.Navigator

class FinishConfigurationContractImpl(
    private val firstStartStateHolder: FirstStartStateHolder
) : FinishConfigurationContract {

    override suspend fun finishConfiguration(navigator: Navigator) {
        firstStartStateHolder.markFirstStartOfApplication()
        navigator.toCalculatorScreen()
    }
}