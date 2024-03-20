package com.xxmrk888ytxx.calculatorscreen.models

enum class CalculatorInputType {
    CLEAR,EXPONENTIATION,PI,DIVISION,MULTIPLICATION,MINUS,PLUS,EQUALS,REMOVE_SYMBOL,POINT,
    ZERO,ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE;

    companion object {
        val numbers = setOf(ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE)
    }
}