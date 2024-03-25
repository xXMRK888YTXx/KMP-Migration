package com.xxmrk888ytxx.securespace

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.application
import com.xxmrk888ytxx.shared.LocalNavigator
import com.xxmrk888ytxx.shared.Navigator



fun application(
    navigator: Navigator,
    content: @Composable ApplicationScope.() -> Unit,
) {
   application {
       CompositionLocalProvider(
           LocalNavigator provides navigator,
           content = { content(this) }
       )
   }
}