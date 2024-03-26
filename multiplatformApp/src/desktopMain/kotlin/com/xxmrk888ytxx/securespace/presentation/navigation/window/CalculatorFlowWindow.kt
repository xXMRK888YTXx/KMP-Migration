package com.xxmrk888ytxx.securespace.presentation.navigation.window

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import com.xxmrk888ytxx.securespace.presentation.navigation.Destination
import com.xxmrk888ytxx.securespace.presentation.navigation.WindowDestination

class CalculatorFlowWindow(
    startDestination: Destination,
    id: Any,
    windowName: String,
) : WindowDestination(startDestination, id, windowName) {
    override val windowSize: DpSize
        get() = DpSize(Dp(400F), Dp(720F))
}