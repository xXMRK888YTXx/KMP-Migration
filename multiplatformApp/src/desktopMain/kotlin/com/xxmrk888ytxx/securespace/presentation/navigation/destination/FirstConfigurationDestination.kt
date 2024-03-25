package com.xxmrk888ytxx.securespace.presentation.navigation.destination

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.xxmrk888ytxx.firstconfigurationscreen.FirstConfigurationScreen
import com.xxmrk888ytxx.firstconfigurationscreen.FirstConfigurationUiModel
import com.xxmrk888ytxx.securespace.presentation.navigation.Destination

class FirstConfigurationDestination(
    private val firstConfigurationUiModel: FirstConfigurationUiModel,
) : Destination(id) {


    @Composable
    override fun Content() {
        val state by firstConfigurationUiModel.state.collectAsState(firstConfigurationUiModel.defaultValue)

        FirstConfigurationScreen(state, firstConfigurationUiModel::onNewEvent)
    }


    companion object {
        const val id = "FirstConfigurationDestination"
    }
}