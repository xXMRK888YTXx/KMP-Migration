package com.xxmrk888ytxx.securespace.domain

import com.xxmrk888ytxx.securespace.domain.models.SessionCryptoKey


interface SessionKeyHolder {

    val key: SessionCryptoKey?

    fun setupKey(sessionCryptoKey: SessionCryptoKey)

    fun resetKey()


    val isKeySetup:Boolean
}