package com.xxmrk888ytxx.securespace.presentation.navigation

interface WindowFactory {
    suspend fun createWindow(
        windowId: Any,
        name: String,
        startDestination: Destination,
    ): WindowDestination
}