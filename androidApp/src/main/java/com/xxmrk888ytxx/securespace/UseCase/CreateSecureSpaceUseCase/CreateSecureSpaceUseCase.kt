package com.xxmrk888ytxx.securespace.UseCase.CreateSecureSpaceUseCase

interface CreateSecureSpaceUseCase {

    suspend fun execute(passwordOfSecureSpace:String)
}