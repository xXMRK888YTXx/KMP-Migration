package com.xxmrk888ytxx.securespace.domain

interface CalculatorPasswordManager {

    suspend fun setupPassword(password:String)

    suspend fun checkPassword(checkingPassword:String) : Boolean

    suspend fun isPasswordSetup() : Boolean
}