package com.xxmrk888ytxx.securespace.domain.SecureSpaceManager

import android.content.Context
import com.xxmrk888ytxx.securespace.domain.SecureSpaceManager
import java.io.File
import javax.inject.Inject

class AndroidSecureSpaceManagerImpl @Inject constructor(
    private val context: Context
) : SecureSpaceManager {

    override val secureSpaceDir: File
        get() = context.getDir("Main_Space",Context.MODE_PRIVATE)

    override val checkFile: File
        get() = File(secureSpaceDir,"check")
}