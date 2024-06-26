package com.xxmrk888ytxx.shared

inline fun <reified T> Array<Any>.getWithCast(index:Int) : T {
    return this[index] as T
}

inline fun <reified T> Array<Any?>.getWithCastOrNull(index:Int) : T? {
    return this[index] as T?
}