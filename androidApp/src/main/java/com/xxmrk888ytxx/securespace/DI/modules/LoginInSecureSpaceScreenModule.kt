package com.xxmrk888ytxx.securespace.DI.modules

import com.xxmrk888ytxx.logininsecurespacescreen.contracts.CheckPasswordFromSecureSpaceContract
import com.xxmrk888ytxx.securespace.glue.LoginInSecureSpaceScreen.CheckPasswordFromSecureSpaceContractImpl
import dagger.Binds
import dagger.Module

@Module
interface LoginInSecureSpaceScreenModule {

    @Binds
    fun bindCheckPasswordFromSecureSpaceContract(
        checkPasswordFromSecureSpaceContractImpl: CheckPasswordFromSecureSpaceContractImpl
    ) : CheckPasswordFromSecureSpaceContract
}