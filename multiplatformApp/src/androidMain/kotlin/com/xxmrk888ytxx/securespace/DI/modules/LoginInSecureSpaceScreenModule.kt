package com.xxmrk888ytxx.securespace.DI.modules

import com.xxmrk888ytxx.logininsecurespacescreen.LoginInSecureSpaceViewModel
import com.xxmrk888ytxx.logininsecurespacescreen.contracts.CheckPasswordFromSecureSpaceContract
import com.xxmrk888ytxx.securespace.domain.useCase.CheckPasswordForLoginInSecureSpaceUseCase
import com.xxmrk888ytxx.securespace.glue.loginInSecureSpaceScreen.CheckPasswordFromSecureSpaceContractImpl
import dagger.Module
import dagger.Provides

@Module
interface LoginInSecureSpaceScreenModule {

    companion object {
        @Provides
        fun bindCheckPasswordFromSecureSpaceContract(
            checkPasswordForLoginInSecureSpaceUseCase: CheckPasswordForLoginInSecureSpaceUseCase
        ) : CheckPasswordFromSecureSpaceContract {
            return CheckPasswordFromSecureSpaceContractImpl(checkPasswordForLoginInSecureSpaceUseCase)
        }

        @Provides
        fun provideLoginInSecureSpaceViewModel(
            checkPasswordFromSecureSpaceContractImpl: CheckPasswordFromSecureSpaceContract
        ) : LoginInSecureSpaceViewModel =
            LoginInSecureSpaceViewModel(checkPasswordFromSecureSpaceContractImpl)
    }
}