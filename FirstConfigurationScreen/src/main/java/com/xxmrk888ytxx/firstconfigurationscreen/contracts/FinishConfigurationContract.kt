package com.xxmrk888ytxx.firstconfigurationscreen.contracts

import com.xxmrk888ytxx.shared.Navigator

interface FinishConfigurationContract {

    suspend fun finishConfiguration(navigator: Navigator)
}