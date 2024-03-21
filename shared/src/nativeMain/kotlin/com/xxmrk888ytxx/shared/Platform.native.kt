package com.xxmrk888ytxx.shared

data object IOSPlatform : Platform {
    override val isDesktop: Boolean
        get() = false
    override val isAndroid: Boolean
        get() = false
    override val isIOS: Boolean
        get() = true
}

actual val currentPlatform: Platform = IOSPlatform