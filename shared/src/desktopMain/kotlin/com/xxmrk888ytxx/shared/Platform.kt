package com.xxmrk888ytxx.shared

data object DesktopPlatform : Platform {
    override val isDesktop: Boolean
        get() = true
    override val isAndroid: Boolean
        get() = false
    override val isIOS: Boolean
        get() = false
}


actual val currentPlatform: Platform = DesktopPlatform