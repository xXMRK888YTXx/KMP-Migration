package com.xxmrk888ytxx.securespace.presentation.navigation.destination

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.xxmrk888ytxx.logininsecurespacescreen.LoginInSecureSpaceScreen
import com.xxmrk888ytxx.logininsecurespacescreen.LoginInSecureSpaceViewModel
import com.xxmrk888ytxx.securespace.presentation.navigation.Destination

class EnterToSecureSpaceDestination(
    private val loginInSecureSpaceViewModel: LoginInSecureSpaceViewModel,
) : Destination("EnterToSecureSpaceDestination") {

    @Composable
    override fun Content() {
        val state by loginInSecureSpaceViewModel.state.collectAsState(loginInSecureSpaceViewModel.defaultValue)
        LoginInSecureSpaceScreen(state, loginInSecureSpaceViewModel::onNewEvent)
    }

    companion object {
        const val id = "EnterToSecureSpaceDestination"
    }
}