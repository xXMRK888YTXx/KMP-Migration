package com.xxmrk888ytxx.shared

interface Platform {
    val isDesktop: Boolean
    val isAndroid: Boolean
    val isIOS: Boolean
}


expect val currentPlatform: Platform