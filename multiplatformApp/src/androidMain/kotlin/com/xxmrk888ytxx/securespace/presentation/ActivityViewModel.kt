package com.xxmrk888ytxx.securespace.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.xxmrk888ytxx.coreandroid.runOnUiThread
import com.xxmrk888ytxx.securespace.domain.OpenSecureScopeByCalculatorInputManager.OpenSecureScopeByCalculatorInputManager
import com.xxmrk888ytxx.shared.Navigator
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

internal class ActivityViewModel @Inject constructor(
    private val openSecureScopeByCalculatorInputManager: OpenSecureScopeByCalculatorInputManager
) : ViewModel(), Navigator {


    var navController:NavController? = null



    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val viewModel: Provider<ActivityViewModel>
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return viewModel.get() as T
        }
    }

    init {
        viewModelScope.launch {
            openSecureScopeByCalculatorInputManager.openSecureSpaceEvent.collect {
                toLoginInSecureSpaceScreen()
            }
        }
    }

    override fun toLoginInSecureSpaceScreen() = runOnUiThread {
        navController?.navigate(Screen.LoginInSecureSpaceScreen.route) { launchSingleTop = true }
    }

    override fun toCalculatorScreen() = runOnUiThread {
        navController?.navigate(Screen.CalculatorScreen.route) {

            popUpTo(Screen.FirstConfigurationScreen.route) { inclusive = true }

            launchSingleTop = true
        }
    }

    override fun toSecureSpaceMainScreen() = runOnUiThread {
        navController?.navigate(Screen.SecureSpaceMainScreen.route) { launchSingleTop = true }
    }
}