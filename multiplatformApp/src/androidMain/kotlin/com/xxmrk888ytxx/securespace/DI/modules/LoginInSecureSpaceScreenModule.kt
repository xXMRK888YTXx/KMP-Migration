package com.xxmrk888ytxx.securespace.DI.modules

import com.xxmrk888ytxx.logininsecurespacescreen.LoginInSecureSpaceViewModel
import com.xxmrk888ytxx.logininsecurespacescreen.contracts.CheckPasswordFromSecureSpaceContract
import com.xxmrk888ytxx.securespace.glue.loginInSecureSpaceScreen.CheckPasswordFromSecureSpaceContractImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface LoginInSecureSpaceScreenModule {

    @Binds
    fun bindCheckPasswordFromSecureSpaceContract(
        checkPasswordFromSecureSpaceContractImpl: CheckPasswordFromSecureSpaceContractImpl
    ) : CheckPasswordFromSecureSpaceContract

    companion object {
        @Provides
        fun provideLoginInSecureSpaceViewModel(
            checkPasswordFromSecureSpaceContractImpl: CheckPasswordFromSecureSpaceContract
        ) : LoginInSecureSpaceViewModel =
            LoginInSecureSpaceViewModel(checkPasswordFromSecureSpaceContractImpl)
    }
}