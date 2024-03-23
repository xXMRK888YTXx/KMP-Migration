package com.xxmrk888ytxx.securespace

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.xxmrk888ytxx.logininsecurespacescreen.LoginInSecureSpaceScreen
import com.xxmrk888ytxx.logininsecurespacescreen.LoginInSecureSpaceViewModel
import com.xxmrk888ytxx.logininsecurespacescreen.contracts.CheckPasswordFromSecureSpaceContract
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

        val h = object : CheckPasswordFromSecureSpaceContract {
            override suspend fun checkPassword(password: String): Boolean {
                return "password" == password
            }

        }


        val model = remember {
            LoginInSecureSpaceViewModel(
                h
            )
        }

        val state by model.state.collectAsState(model.defaultValue)

        CompositionLocalProvider(
            LocalNavigator provides navigator
        ) {
            LoginInSecureSpaceScreen(
                state,
                onEvent = model::onNewEvent
            )
        }
    }
}