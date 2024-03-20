package com.xxmrk888ytxx.securespace.glue.FirstConfigurationScreen

import com.xxmrk888ytxx.coreandroid.ShareInterfaces.Navigator
import com.xxmrk888ytxx.firstconfigurationscreen.contracts.FinishConfigurationContract
import com.xxmrk888ytxx.securespace.domain.FirstStartStateHolder.FirstStartStateHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FinishConfigurationContractImpl @Inject constructor(
    private val firstStartStateHolder: FirstStartStateHolder
) : FinishConfigurationContract {

    override suspend fun finishConfiguration(navigator: Navigator) {
        firstStartStateHolder.markFirstStartOfApplication()

        withContext(Dispatchers.Main) {
            navigator.toCalculatorScreen()
        }
    }
}