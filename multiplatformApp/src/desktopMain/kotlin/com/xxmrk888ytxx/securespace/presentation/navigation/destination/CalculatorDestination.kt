package com.xxmrk888ytxx.securespace.presentation.navigation.destination

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.xxmrk888ytxx.calculatorscreen.CalculatorScreen
import com.xxmrk888ytxx.calculatorscreen.CalculatorViewModel
import com.xxmrk888ytxx.securespace.presentation.navigation.Destination

class CalculatorDestination(
    private val calculatorViewModel: CalculatorViewModel,
) : Destination(id) {

    @Composable
    override fun Content() {
        val state by calculatorViewModel.state.collectAsState(calculatorViewModel.defaultValue)
        CalculatorScreen(state, calculatorViewModel::onNewEvent)
    }

    companion object {
        const val id = "CalculatorDestination"
    }
}