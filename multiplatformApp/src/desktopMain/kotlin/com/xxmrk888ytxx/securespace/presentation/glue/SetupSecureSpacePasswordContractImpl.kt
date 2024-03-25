package com.xxmrk888ytxx.securespace.presentation.glue

import com.xxmrk888ytxx.firstconfigurationscreen.contracts.SetupSecureSpacePasswordContract
import org.koin.core.component.KoinComponent

class SetupSecureSpacePasswordContractImpl : SetupSecureSpacePasswordContract, KoinComponent {
    override suspend fun setupPassword(password: String) {

    }
}