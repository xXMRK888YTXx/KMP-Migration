package com.xxmrk888ytxx.securespace.DI.modules

import com.xxmrk888ytxx.securespace.UseCase.CheckPasswordForLoginInSecureSpaceUseCase.CheckPasswordForLoginInSecureSpaceUseCase
import com.xxmrk888ytxx.securespace.UseCase.CheckPasswordForLoginInSecureSpaceUseCase.CheckPasswordForLoginInSecureSpaceUseCaseImpl
import com.xxmrk888ytxx.securespace.UseCase.CreateSecureSpaceUseCase.CreateSecureSpaceUseCase
import com.xxmrk888ytxx.securespace.UseCase.CreateSecureSpaceUseCase.CreateSecureSpaceUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCasesModule {

    @Binds
    fun bindCreateSecureSpaceUseCase(
        createSecureSpaceUseCaseImpl: CreateSecureSpaceUseCaseImpl
    ) : CreateSecureSpaceUseCase

    @Binds
    fun bindCheckPasswordForLoginInSecureSpaceUseCase(
        checkPasswordForLoginInSecureSpaceUseCaseImpl: CheckPasswordForLoginInSecureSpaceUseCaseImpl
    ) : CheckPasswordForLoginInSecureSpaceUseCase
}