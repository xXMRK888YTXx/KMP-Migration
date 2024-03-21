package com.xxmrk888ytxx.shared


data object AndroidPlatform : Platform {
    override val isDesktop: Boolean
        get() = false
    override val isAndroid: Boolean
        get() = true
    override val isIOS: Boolean
        get() = false
}

actual val currentPlatform: Platform = AndroidPlatform