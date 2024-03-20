package com.xxmrk888ytxx.securespace.domain.SecureSpaceManager

import java.io.File
import kotlin.text.Charsets.UTF_8

interface SecureSpaceManager {

    val secureSpaceDir:File

    val checkFile:File

    companion object {
        val CHECK_VERIFICATION_KEY = "GOTCHA".toByteArray(UTF_8)
    }
}