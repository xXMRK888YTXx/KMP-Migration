package com.xxmrk888ytxx.securespace.presentation.navigation

import com.xxmrk888ytxx.securespace.DesktopApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

abstract class WindowDestination(
    startDestination: Destination,
    val id: Any,
    val windowName: String,
) {

    private val mutex = Mutex()
    private val destinations = MutableStateFlow(listOf(startDestination))

    open val currentDestination =
        destinations.map { it.lastOrNull() ?: error("Destinations of window $this is empty") }
            .stateIn(DesktopApplication.scope, SharingStarted.Eagerly,null)

    open suspend fun addDestination(destination: Destination) = mutex.withLock {
        destinations.update { it.toMutableList().apply { add(destination) } }
    }

    open suspend fun removeDestination(destinationId:Any) = mutex.withLock {
        destinations.update { it.filter { it.id != destinationId } }
    }

    open suspend fun popCurrentDestination(): Boolean = mutex.withLock {
        if (destinations.value.size <= 1) return@withLock false
        destinations.update { it.dropLast(1) }
        return@withLock true
    }
}