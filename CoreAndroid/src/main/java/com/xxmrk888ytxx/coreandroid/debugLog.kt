@file:OptIn(UseLoggerInterface::class)

package com.xxmrk888ytxx.androidcore

import com.xxmrk888ytxx.coreandroid.AndroidLogger
import com.xxmrk888ytxx.coreandroid.annotations.UseLoggerInterface
import com.xxmrk888ytxx.coreandroid.annotations.UsedFastLogger

@UsedFastLogger
fun fastDebugLog(m:String) {
    AndroidLogger.debug(m)
}

@UsedFastLogger
fun fastDebugLog(m:Any?) {
    AndroidLogger.debug(m)
}

@UsedFastLogger
fun fastDebugLog(m:Throwable) {
    AndroidLogger.debug(m)
}