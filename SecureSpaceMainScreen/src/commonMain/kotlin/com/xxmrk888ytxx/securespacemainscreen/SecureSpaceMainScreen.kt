package com.xxmrk888ytxx.securespacemainscreen

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.xxmrk888ytxx.securespacemainscreen.models.ScreenState
import com.xxmrk888ytxx.shared.mvi.UiEvent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import securespace.securespacemainscreen.generated.resources.Res
import securespace.securespacemainscreen.generated.resources.some_ui

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SecureSpaceMainScreen(
    screenState: ScreenState,
    onEvent:(UiEvent) -> Unit
) {
    Scaffold {
        Text(text = stringResource(Res.string.some_ui))
    }
}