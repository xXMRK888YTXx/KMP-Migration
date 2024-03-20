package com.xxmrk888ytxx.securespace.glue.LoginInSecureSpaceScreen

import com.xxmrk888ytxx.logininsecurespacescreen.contracts.CheckPasswordFromSecureSpaceContract
import com.xxmrk888ytxx.securespace.UseCase.CheckPasswordForLoginInSecureSpaceUseCase.CheckPasswordForLoginInSecureSpaceUseCase
import javax.inject.Inject

class CheckPasswordFromSecureSpaceContractImpl @Inject constructor(
    private val checkPasswordForLoginInSecureSpaceUseCase: CheckPasswordForLoginInSecureSpaceUseCase
) : CheckPasswordFromSecureSpaceContract {

    override suspend fun checkPassword(password: String): Boolean {
        return checkPasswordForLoginInSecureSpaceUseCase.execute(password)
    }
}