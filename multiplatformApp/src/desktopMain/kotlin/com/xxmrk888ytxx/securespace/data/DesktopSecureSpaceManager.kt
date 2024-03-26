package com.xxmrk888ytxx.securespace.data

import com.xxmrk888ytxx.securespace.domain.SecureSpaceManager
import java.io.File

class DesktopSecureSpaceManager(
    private val baseFileDir: File,
) : SecureSpaceManager {
    override val secureSpaceDir: File
        get() = File(baseFileDir, "Main_Space")
    override val checkFile: File
        get() = File(baseFileDir, "check")
}