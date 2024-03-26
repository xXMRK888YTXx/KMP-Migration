package com.xxmrk888ytxx.securespace

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import com.xxmrk888ytxx.securespace.presentation.navigation.Destination
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    DesktopApplication.onApplicationStarted()
    ui()
}

fun ui() = application(navigator = DesktopApplication) {
    val windowNavigator = remember { DesktopApplication.windowNavigator }
    val windows by windowNavigator.currentWindows.collectAsState(emptyList())

    windows.forEach { window ->
        val windowState = rememberWindowState(
            size = window.windowSize
        )

        Window(
            onCloseRequest = this::exitApplication,
            title = window.windowName,
            state = windowState
        ) {
            val currentDestination: Destination? by window.currentDestination.collectAsState()
            currentDestination?.Content()
        }
    }
}