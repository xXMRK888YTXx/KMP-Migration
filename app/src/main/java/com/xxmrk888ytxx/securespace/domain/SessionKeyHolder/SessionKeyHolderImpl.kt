package com.xxmrk888ytxx.securespace.domain.SessionKeyHolder

import com.xxmrk888ytxx.securespace.domain.SessionKeyHolder.models.SessionCryptoKey
import javax.inject.Inject

class SessionKeyHolderImpl @Inject constructor() : SessionKeyHolder {

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