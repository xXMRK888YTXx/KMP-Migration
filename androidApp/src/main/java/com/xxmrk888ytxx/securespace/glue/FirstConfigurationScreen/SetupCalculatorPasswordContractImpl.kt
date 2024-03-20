package com.xxmrk888ytxx.securespace.glue.FirstConfigurationScreen

import com.xxmrk888ytxx.firstconfigurationscreen.contracts.SetupCalculatorPasswordContract
import com.xxmrk888ytxx.securespace.domain.CalculatorPasswordManager.CalculatorPasswordManager
import javax.inject.Inject

class SetupCalculatorPasswordContractImpl @Inject constructor(
    private val calculatorPasswordManager: CalculatorPasswordManager
) : SetupCalculatorPasswordContract {


    override suspend fun setupPassword(password: String) {
        if(!password.isCalculatorPasswordValid()) throw IllegalArgumentException("Password invalid")

        calculatorPasswordManager.setupPassword(password)
    }

    private fun String.isCalculatorPasswordValid() : Boolean {
        if(length > 10) return false

        var isPointAlreadyBeen = false

        forEach {
            when {
                it == '.' -> {
                    if(isPointAlreadyBeen) return false

                    isPointAlreadyBeen = true
                }

                !it.isDigit() -> return false
            }
        }

        return true
    }
}