package com.xxmrk888ytxx.shared

import androidx.compose.ui.graphics.Color

interface ComposeUiController {

    fun setupStatusBar(color:Color)

    fun setupNavigationBar(color: Color)

    fun resetStatusBarColor()

    fun resetNavigationBarColor()
}