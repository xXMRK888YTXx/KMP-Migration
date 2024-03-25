package com.xxmrk888ytxx.calculatorscreen.models

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

sealed class CalculatorButtonModel(open val color: Color, open val action: () -> Unit) {

    data class Text(val text: String, override val color: Color, override val action: () -> Unit) :
        CalculatorButtonModel(color, action)

    data class Icon @OptIn(ExperimentalResourceApi::class) constructor(
        val iconId: DrawableResource,
        override val color: Color,
        override val action: () -> Unit,
    ) : CalculatorButtonModel(color, action)

}
