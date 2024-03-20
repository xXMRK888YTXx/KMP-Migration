package com.xxmrk888ytxx.securespace.UseCase.CheckPasswordForLoginInSecureSpaceUseCase

interface CheckPasswordForLoginInSecureSpaceUseCase {

    suspend fun execute(password:String) : Boolean
}