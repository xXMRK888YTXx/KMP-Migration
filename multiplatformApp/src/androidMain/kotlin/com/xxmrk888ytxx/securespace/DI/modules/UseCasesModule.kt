package com.xxmrk888ytxx.securespace.DI.modules

import com.xxmrk888ytxx.securespace.domain.useCase.CheckPasswordForLoginInSecureSpaceUseCase
import com.xxmrk888ytxx.securespace.data.CheckPasswordForLoginInSecureSpaceUseCaseImpl
import com.xxmrk888ytxx.securespace.domain.useCase.CreateSecureSpaceUseCase
import com.xxmrk888ytxx.securespace.data.useCase.CreateSecureSpaceUseCaseImpl
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