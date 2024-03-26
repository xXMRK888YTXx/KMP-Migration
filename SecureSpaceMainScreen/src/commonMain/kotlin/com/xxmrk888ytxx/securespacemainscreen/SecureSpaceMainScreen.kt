package com.xxmrk888ytxx.securespacemainscreen

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.xxmrk888ytxx.securespacemainscreen.models.ScreenState
import com.xxmrk888ytxx.shared.mvi.UiEvent
import dev.icerock.moko.resources.compose.stringResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SecureSpaceMainScreen(
    screenState: ScreenState,
    onEvent:(UiEvent) -> Unit
) {
    Scaffold {
        Text(text = stringResource(MR.strings.some_ui))
    }
}