package com.xxmrk888ytxx.securespace.extensions

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.compose.AppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.xxmrk888ytxx.corecompose.ComposeUiController
import com.xxmrk888ytxx.corecompose.LocalComposeUiController
import com.xxmrk888ytxx.securespace.App
import com.xxmrk888ytxx.securespace.DI.AppComponent

internal val Context.appComponent : AppComponent
    get() = when(this) {
        is App -> appComponent
        else -> applicationContext.appComponent
   }

/**
 * [Ru]
 * Данная функция предназначена для, того что бы созданная viewModel
 * была привязана к жизненному циклу экрана(не Activity, а к NavBackStackEntry, или если ещё проще,
 * то что бы viewModel жила пока пользователь окончательно не уйдет с экрана)
 */
/**
 * [En]
 * This function is intended to ensure that the created viewModel
 * was tied to the screen life cycle (not Activity, but to NavBackStackEntry, or even simpler,
 * so that the viewModel would live until the user finally leaves the screen)
 */
@Suppress("UNCHECKED_CAST")
@Composable
inline fun <reified T : ViewModel> composeViewModel(
    key: String? = null,
    crossinline viewModelInstanceCreator: () -> T
): T = androidx.lifecycle.viewmodel.compose.viewModel (
    modelClass = T::class.java,
    key = key,
    factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return viewModelInstanceCreator() as T
        }
    }
)

fun ComponentActivity.setContentWithTheme(
    useDynamicColors: Boolean = true,
    useDarkTheme: Boolean? = null,
    content: @Composable () -> Unit,
) {

    setContent {
        val systemUiController = rememberSystemUiController()
        val backgroundColor = MaterialTheme.colorScheme.background

        val composeUiController = remember<ComposeUiController> {
            object : ComposeUiController {
                override fun setupStatusBar(color: Color) {
                    systemUiController.setStatusBarColor(color)
                }

                override fun setupNavigationBar(color: Color) {
                    systemUiController.setNavigationBarColor(color)
                }

                override fun resetStatusBarColor() {
                    systemUiController.setStatusBarColor(backgroundColor)

                }

                override fun resetNavigationBarColor() {
                    systemUiController.setNavigationBarColor(backgroundColor)
                }

            }
        }

        CompositionLocalProvider(
            LocalComposeUiController provides composeUiController
        ) {
            AppTheme(
                useDarkTheme = useDarkTheme ?: isSystemInDarkTheme(),
                useDynamicColors = useDynamicColors,
                content = content
            )
        }
    }
}