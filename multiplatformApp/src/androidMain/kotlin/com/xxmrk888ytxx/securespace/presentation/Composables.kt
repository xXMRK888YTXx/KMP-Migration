package com.xxmrk888ytxx.securespace.presentation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.xxmrk888ytxx.calculatorscreen.CalculatorScreen
import com.xxmrk888ytxx.calculatorscreen.CalculatorViewModel
import com.xxmrk888ytxx.firstconfigurationscreen.FirstConfigurationScreen
import com.xxmrk888ytxx.firstconfigurationscreen.FirstConfigurationUiModel
import com.xxmrk888ytxx.logininsecurespacescreen.LoginInSecureSpaceScreen
import com.xxmrk888ytxx.logininsecurespacescreen.LoginInSecureSpaceViewModel
import com.xxmrk888ytxx.securespace.extensions.composeViewModel
import com.xxmrk888ytxx.securespacemainscreen.SecureSpaceMainScreen
import com.xxmrk888ytxx.securespacemainscreen.SecureSpaceMainViewModel
import com.xxmrk888ytxx.shared.AndroidUiModelWrapper
import javax.inject.Provider

fun NavGraphBuilder.calculatorScreen(
    calculatorViewModel: Provider<CalculatorViewModel>,
) {
    composable(Screen.CalculatorScreen.route) {
        val viewModel = composeViewModel {
            calculatorViewModel.get()
        }

        val state by viewModel.state.collectAsStateWithLifecycle(
            initialValue = viewModel.defaultValue,
            lifecycle = it.lifecycle
        )

        CalculatorScreen(screenState = state, onEvent = viewModel::onNewEvent)
    }
}

fun NavGraphBuilder.loginInSecureSpaceScreen(
    loginInSecureSpaceViewModel: Provider<LoginInSecureSpaceViewModel>,
) {
    composable(Screen.LoginInSecureSpaceScreen.route) {
        val viewModel = composeViewModel {
            loginInSecureSpaceViewModel.get()
        }

        val state by viewModel.state.collectAsStateWithLifecycle(
            initialValue = viewModel.defaultValue,
            lifecycle = it.lifecycle
        )

        LoginInSecureSpaceScreen(state, viewModel::onNewEvent)
    }
}

fun NavGraphBuilder.firstConfigurationScreen(
    firstConfigurationUiModel: Provider<FirstConfigurationUiModel>,
) {
    composable(Screen.FirstConfigurationScreen.route) {
        val viewModel = composeViewModel {
            AndroidUiModelWrapper(firstConfigurationUiModel.get())
        }

        val state by viewModel.state.collectAsStateWithLifecycle(
            initialValue = viewModel.defaultValue,
            lifecycle = it.lifecycle
        )

        FirstConfigurationScreen(state, viewModel::onNewEvent)
    }
}

fun NavGraphBuilder.secureSpaceMainScreen(
    secureSpaceMainViewModel: Provider<SecureSpaceMainViewModel>,
) {
    composable(Screen.SecureSpaceMainScreen.route) {
        val viewModel = composeViewModel {
            secureSpaceMainViewModel.get()
        }

        val state by viewModel.state.collectAsStateWithLifecycle(
            initialValue = viewModel.defaultValue,
            lifecycle = it.lifecycle
        )

        SecureSpaceMainScreen(
            screenState = state,
            onEvent = viewModel::onNewEvent
        )
    }
}