package com.xxmrk888ytxx.logininsecurespacescreen.contracts

interface CheckPasswordFromSecureSpaceContract {

    suspend fun checkPassword(password:String) : Boolean
}