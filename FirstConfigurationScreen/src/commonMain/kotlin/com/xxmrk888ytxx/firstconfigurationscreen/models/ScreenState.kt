package com.xxmrk888ytxx.firstconfigurationscreen.models

data class ScreenState(
    val screenType: ScreenType = ScreenType.CALCULATION_PASSWORD_SETUP,
    val passwordFromCalculator: String = "",
    val repeatPasswordFromCalculator: String = "",
    val passwordOfSecureSpace:String = "",
    val repeatPasswordOfSecureSpace:String = "",
    val isLoading:Boolean = false
)
