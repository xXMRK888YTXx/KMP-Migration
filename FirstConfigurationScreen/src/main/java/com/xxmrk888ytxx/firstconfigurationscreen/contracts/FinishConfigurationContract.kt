package com.xxmrk888ytxx.firstconfigurationscreen.contracts

import com.xxmrk888ytxx.coreandroid.ShareInterfaces.Navigator

interface FinishConfigurationContract {

    suspend fun finishConfiguration(navigator: Navigator)
}