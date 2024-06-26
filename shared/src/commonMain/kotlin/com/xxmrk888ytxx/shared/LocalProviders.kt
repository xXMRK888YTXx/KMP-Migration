package com.xxmrk888ytxx.shared

import androidx.compose.runtime.compositionLocalOf
import com.xxmrk888ytxx.shared.Navigator


val LocalNavigator = compositionLocalOf<Navigator> {
    error("Navigator is not provided")
}

val LocalComposeUiController = compositionLocalOf<ComposeUiController> {
    error("LocalComposeUiController is not provided")
}