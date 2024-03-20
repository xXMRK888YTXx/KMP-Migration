package com.xxmrk888ytxx.securespace.glue.FirstConfigurationScreen

import com.xxmrk888ytxx.firstconfigurationscreen.contracts.SetupSecureSpacePasswordContract
import com.xxmrk888ytxx.securespace.UseCase.CreateSecureSpaceUseCase.CreateSecureSpaceUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SetupSecureSpacePasswordContractImpl @Inject constructor(
    private val createSecureSpaceUseCase: CreateSecureSpaceUseCase
) : SetupSecureSpacePasswordContract {

    override suspend fun setupPassword(password: String) = withContext(Dispatchers.IO) {
        createSecureSpaceUseCase.execute(password)
    }
}