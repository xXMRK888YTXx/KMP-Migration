package com.xxmrk888ytxx.securespace.glue.firstConfigurationScreen

import com.xxmrk888ytxx.firstconfigurationscreen.contracts.SetupSecureSpacePasswordContract
import com.xxmrk888ytxx.securespace.domain.useCase.CreateSecureSpaceUseCase
import com.xxmrk888ytxx.securespace.expected.ioDispatcher
import kotlinx.coroutines.withContext

class SetupSecureSpacePasswordContractImpl constructor(
    private val createSecureSpaceUseCase: CreateSecureSpaceUseCase
) : SetupSecureSpacePasswordContract {

    override suspend fun setupPassword(password: String) = withContext(ioDispatcher) {
        createSecureSpaceUseCase.execute(password)
    }
}