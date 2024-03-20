package com.xxmrk888ytxx.calculatorscreen.models

import androidx.annotation.IdRes
import androidx.compose.ui.graphics.Color

sealed class CalculatorButtonModel(open val color:Color,open val action:() -> Unit) {

    data class Text(val text:String, override val color: Color,override val action:() -> Unit) : CalculatorButtonModel(color,action)

    data class Icon(@IdRes val iconId:Int, override val color: Color,override val action:() -> Unit) : CalculatorButtonModel(color,action)

}
