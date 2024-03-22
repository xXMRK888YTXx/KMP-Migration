package com.xxmrk888ytxx.securespace

import com.xxmrk888ytxx.firstconfigurationscreen.FirstConfigurationScreen
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.xxmrk888ytxx.firstconfigurationscreen.FirstConfigurationUiModel
import com.xxmrk888ytxx.firstconfigurationscreen.contracts.FinishConfigurationContract
import com.xxmrk888ytxx.firstconfigurationscreen.contracts.SetupCalculatorPasswordContract
import com.xxmrk888ytxx.firstconfigurationscreen.contracts.SetupSecureSpacePasswordContract
import com.xxmrk888ytxx.shared.LocalNavigator
import com.xxmrk888ytxx.shared.Navigator

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "KotlinProject") {
        val navigator = object : Navigator {
            override fun toLoginInSecureSpaceScreen() {

            }

            override fun toCalculatorScreen() {
            }

            override fun toSecureSpaceMainScreen() {
            }

        }

        val f = object : SetupCalculatorPasswordContract {
            override suspend fun setupPassword(password: String) {

            }

        }
        val v = object : SetupSecureSpacePasswordContract {
            override suspend fun setupPassword(password: String) {

            }

        }
        val b = object : FinishConfigurationContract {
            override suspend fun finishConfiguration(navigator: Navigator) {

            }

        }

        val model = remember {
            FirstConfigurationUiModel(
                f,
                v,
                b
            )
        }

        val state by model.state.collectAsState(model.defaultValue)

        CompositionLocalProvider(
            LocalNavigator provides navigator
        ) {
            FirstConfigurationScreen(
                state,
                onEvent = model::onNewEvent
            )
        }
    }
}