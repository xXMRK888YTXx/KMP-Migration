package com.xxmrk888ytxx.preferencesstorage.utils

import java.util.Base64


internal object Base64Converter {

    fun bytesToString(value:ByteArray) : String {
        return Base64.getEncoder().encodeToString(value)
    }

    fun stringToBytes(value: String) : ByteArray {
        return Base64.getDecoder().decode(value)
    }
}