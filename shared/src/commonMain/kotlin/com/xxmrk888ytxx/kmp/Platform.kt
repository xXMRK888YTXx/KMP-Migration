package com.xxmrk888ytxx.kmp

interface Platform {
    val isDesktop: Boolean
    val isAndroid: Boolean
    val isIOS: Boolean
}


expect val currentPlatform: Platform

inline fun <reified T : Platform> getTypedPlatformClass(): T {
    return currentPlatform as? T
        ?: error("Cannot receive ${T::class.simpleName} platform class. Current platform class is ${currentPlatform::class.simpleName}")
}