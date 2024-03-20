package com.xxmrk888ytxx.securespace.domain.SessionKeyHolder

import com.xxmrk888ytxx.securespace.domain.SessionKeyHolder.models.SessionCryptoKey

interface SessionKeyHolder {

    val key: SessionCryptoKey?

    fun setupKey(sessionCryptoKey: SessionCryptoKey)

    fun resetKey()


    val isKeySetup:Boolean
}