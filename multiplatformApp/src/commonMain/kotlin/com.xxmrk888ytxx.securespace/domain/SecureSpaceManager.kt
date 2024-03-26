package com.xxmrk888ytxx.securespace.domain

import java.io.File

interface SecureSpaceManager {

    val secureSpaceDir:File

    val checkFile:File

    companion object {
        val CHECK_VERIFICATION_KEY = "GOTCHA".encodeToByteArray()
    }
}