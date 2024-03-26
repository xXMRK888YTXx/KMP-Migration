package com.xxmrk888ytxx.securespace.data

import com.xxmrk888ytxx.securespace.domain.SessionKeyHolder
import com.xxmrk888ytxx.securespace.domain.models.SessionCryptoKey

class SessionKeyHolderImpl constructor() : SessionKeyHolder {

    private var currentKey: SessionCryptoKey? = null

    override val key: SessionCryptoKey?
        get() = currentKey

    override fun setupKey(sessionCryptoKey: SessionCryptoKey) {
        currentKey = sessionCryptoKey
    }

    override fun resetKey() {
        currentKey = null
    }

    override val isKeySetup: Boolean
        get() = currentKey != null
}