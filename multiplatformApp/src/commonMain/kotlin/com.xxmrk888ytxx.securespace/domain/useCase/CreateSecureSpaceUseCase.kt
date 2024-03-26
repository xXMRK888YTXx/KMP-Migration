package com.xxmrk888ytxx.securespace.domain.useCase

interface CreateSecureSpaceUseCase {

    suspend fun execute(passwordOfSecureSpace:String)
}