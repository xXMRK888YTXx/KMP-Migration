package com.xxmrk888ytxx.securespace.domain.CalculatorPasswordManager

interface CalculatorPasswordManager {

    suspend fun setupPassword(password:String)

    suspend fun checkPassword(checkingPassword:String) : Boolean

    suspend fun isPasswordSetup() : Boolean
}