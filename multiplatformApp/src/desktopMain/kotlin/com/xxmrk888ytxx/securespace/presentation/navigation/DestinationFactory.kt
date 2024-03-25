package com.xxmrk888ytxx.securespace.presentation.navigation

interface DestinationFactory {
    suspend fun createDestination(id:Any) : Destination
}