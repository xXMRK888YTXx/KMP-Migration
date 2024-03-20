package com.xxmrk888ytxx.securespace.presentation

sealed class Screen(val route:String) {

    data object CalculatorScreen : Screen("CalculatorScreen")

    data object LoginInSecureSpaceScreen : Screen("LoginInSecureSpaceScreen")

    data object FirstConfigurationScreen : Screen("FirstConfigurationScreen")

    data object SecureSpaceMainScreen : Screen("SecureSpaceMainScreen")
}
