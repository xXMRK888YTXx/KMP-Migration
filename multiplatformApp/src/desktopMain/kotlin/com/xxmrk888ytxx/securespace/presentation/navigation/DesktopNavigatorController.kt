package com.xxmrk888ytxx.securespace.presentation.navigation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class DesktopNavigatorController(
    private val navigationScope: CoroutineScope,
    private val destinationFactory: DestinationFactory,
    private val windowFactory: WindowFactory,
) : WindowNavigatorController {

    private val _currentWindows = MutableStateFlow(emptyList<WindowDestination>())
    override val currentWindows: StateFlow<List<WindowDestination>> = _currentWindows.asStateFlow()

    @OptIn(ObsoleteCoroutinesApi::class)
    private val navigatorActor =
        navigationScope.actor<NavigatorCommand>(capacity = Channel.BUFFERED) {
            for (command in this) {
                when (command) {
                    is NavigatorCommand.AddWindow -> {
                        val destination =
                            destinationFactory.createDestination(command.startDestinationId)
                        val window = windowFactory.createWindow(
                            command.windowId,
                            command.windowName,
                            destination
                        )
                        _currentWindows.update { it.toMutableList().apply { add(window) } }
                    }

                    is NavigatorCommand.RemoveWindow -> {
                        _currentWindows.update { windowList ->
                            windowList.filter { it.id != command.windowId }
                        }
                    }

                    is NavigatorCommand.AddDestinationToWindow -> {
                        val destination =
                            destinationFactory.createDestination(command.destinationId)
                        val window = findWindow(command.windowId)
                        window.addDestination(destination)
                    }

                    is NavigatorCommand.PopCurrentDestinationFromWindow -> {
                        val window = findWindow(command.windowId)
                        window.popCurrentDestination()
                    }

                    is NavigatorCommand.RemoveDestinationFromWindow -> {
                        val window = findWindow(command.windowId)
                        window.removeDestination(command.destinationId)
                    }
                }
            }
        }

    override fun addWindow(windowId: Any, startDestinationId: Any, windowName: String) {
        navigatorActor.trySend(NavigatorCommand.AddWindow(windowId, startDestinationId, windowName))
    }

    override fun removeWindow(windowId: Any) {
        navigatorActor.trySend(NavigatorCommand.RemoveWindow(windowId))
    }

    override fun addDestination(windowId: Any, destinationId: Any) {
        navigatorActor.trySend(NavigatorCommand.AddDestinationToWindow(windowId, destinationId))
    }

    override fun removeDestination(windowId: Any, destinationId: Any) {
        navigatorActor.trySend(
            NavigatorCommand.RemoveDestinationFromWindow(
                windowId,
                destinationId
            )
        )

    }

    override fun popDestinationFromWindow(windowId: Any) {
        navigatorActor.trySend(NavigatorCommand.PopCurrentDestinationFromWindow(windowId))
    }

    private suspend fun findWindow(windowId: Any): WindowDestination =
        _currentWindows.value.firstOrNull { it.id == windowId } ?: error("Window not found")


    private sealed interface NavigatorCommand {
        data class AddWindow(
            val windowId: Any,
            val startDestinationId: Any,
            val windowName: String,
        ) : NavigatorCommand

        data class RemoveWindow(val windowId: Any) : NavigatorCommand
        data class AddDestinationToWindow(val windowId: Any, val destinationId: Any) :
            NavigatorCommand

        data class RemoveDestinationFromWindow(val windowId: Any, val destinationId: Any) :
            NavigatorCommand

        data class PopCurrentDestinationFromWindow(val windowId: Any) : NavigatorCommand

    }
}