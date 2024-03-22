package com.xxmrk888ytxx.securespace.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.xxmrk888ytxx.shared.Navigator
import com.xxmrk888ytxx.shared.LocalNavigator


/**
 * [Ru]
 * Эта функция оборачивает [NavHost] и предоставляет [Navigator]
 * с помощью CompositionLocalProvider
 */

/**
 * [En]
 * This function wrapping [NavHost] and provided
 * [Navigator] by CompositionLocalProvider
 */
@Composable
fun NavigationHost(
    navController: NavHostController,
    navigator: Navigator,
    startDestination: String,
    builder: NavGraphBuilder.() -> Unit
) {
    CompositionLocalProvider(
        LocalNavigator provides navigator,
    ) {
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            navController = navController,
            startDestination = startDestination,
            builder = builder
        )
    }
}