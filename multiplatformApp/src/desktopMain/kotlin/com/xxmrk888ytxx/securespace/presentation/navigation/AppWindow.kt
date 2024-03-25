package com.xxmrk888ytxx.securespace.presentation.navigation

sealed class AppWindow(val id: Any) {
    data object CalculatorFlow : AppWindow("CalculatorFlow")
    data object SecureSpaceFlow : AppWindow("SecureSpaceFlow")
}