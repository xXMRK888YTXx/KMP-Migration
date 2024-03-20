package com.xxmrk888ytxx.corecompose

import androidx.compose.runtime.compositionLocalOf
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.Navigator


val LocalNavigator = compositionLocalOf<Navigator> {
    error("Navigator is not provided")
}

val LocalComposeUiController = compositionLocalOf<ComposeUiController> {
    error("LocalComposeUiController is not provided")
}