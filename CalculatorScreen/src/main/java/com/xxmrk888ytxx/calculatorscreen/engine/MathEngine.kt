package com.xxmrk888ytxx.calculatorscreen.engine

import android.icu.math.BigDecimal
import kotlinx.coroutines.flow.Flow

interface MathEngine {

    val inputtedSymbols: Flow<List<MathSymbol>>

    val result: Flow<MathResult>

    suspend fun input(mathSymbol: MathSymbol)

    suspend fun setInput(number: BigDecimal)

    suspend fun removeLast()

    suspend fun clear()
}