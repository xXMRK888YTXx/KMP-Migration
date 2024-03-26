package com.xxmrk888ytxx.securespace.domain.useCase

interface CheckPasswordForLoginInSecureSpaceUseCase {

    suspend fun execute(password:String) : Boolean
}