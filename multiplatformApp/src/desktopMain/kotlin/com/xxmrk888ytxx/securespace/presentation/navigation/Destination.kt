package com.xxmrk888ytxx.securespace.presentation.navigation

import androidx.compose.runtime.Composable

abstract class Destination(val id:Any) {
    @Composable
    abstract fun Content()
}