package com.xxmrk888ytxx.securespace.expected

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
actual val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default