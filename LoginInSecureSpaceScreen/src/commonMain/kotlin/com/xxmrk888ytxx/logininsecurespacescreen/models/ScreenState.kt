package com.xxmrk888ytxx.logininsecurespacescreen.models

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.text.input.TextFieldValue

data class ScreenState @OptIn(ExperimentalFoundationApi::class) constructor(
    val enteredPassword: TextFieldValue = TextFieldValue(""),
)
