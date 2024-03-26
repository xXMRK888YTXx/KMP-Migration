package com.xxmrk888ytxx.securespace.glue.loginInSecureSpaceScreen

import com.xxmrk888ytxx.logininsecurespacescreen.contracts.CheckPasswordFromSecureSpaceContract
import com.xxmrk888ytxx.securespace.domain.useCase.CheckPasswordForLoginInSecureSpaceUseCase

class CheckPasswordFromSecureSpaceContractImpl constructor(
    private val checkPasswordForLoginInSecureSpaceUseCase: CheckPasswordForLoginInSecureSpaceUseCase
) : CheckPasswordFromSecureSpaceContract {

    override suspend fun checkPassword(password: String): Boolean {
        return checkPasswordForLoginInSecureSpaceUseCase.execute(password)
    }
}