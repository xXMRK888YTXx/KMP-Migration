package com.xxmrk888ytxx.firstconfigurationscreen.models

import androidx.compose.ui.text.input.TextFieldValue

data class ScreenState(
    val screenType: ScreenType = ScreenType.CALCULATION_PASSWORD_SETUP,
    val passwordFromCalculator: TextFieldValue = TextFieldValue(),
    val repeatPasswordFromCalculator: TextFieldValue = TextFieldValue(),
    val passwordOfSecureSpace: TextFieldValue = TextFieldValue(),
    val repeatPasswordOfSecureSpace: TextFieldValue = TextFieldValue(),
    val isLoading: Boolean = false,
)
