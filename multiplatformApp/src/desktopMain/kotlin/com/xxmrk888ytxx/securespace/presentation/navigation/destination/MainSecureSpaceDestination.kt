package com.xxmrk888ytxx.securespace.presentation.navigation.destination

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.xxmrk888ytxx.securespace.presentation.navigation.Destination
import com.xxmrk888ytxx.securespacemainscreen.SecureSpaceMainScreen
import com.xxmrk888ytxx.securespacemainscreen.SecureSpaceMainViewModel

class MainSecureSpaceDestination(
    private val secureSpaceViewModel: SecureSpaceMainViewModel
) : Destination(id) {

    @Composable
    override fun Content() {
        val state by secureSpaceViewModel.state.collectAsState(secureSpaceViewModel.defaultValue)
        SecureSpaceMainScreen(state,secureSpaceViewModel::onNewEvent)
    }

    companion object {
        const val id = "MainSecureSpaceDestination"
    }
}