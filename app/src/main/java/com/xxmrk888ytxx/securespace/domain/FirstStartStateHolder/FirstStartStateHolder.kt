package com.xxmrk888ytxx.securespace.domain.FirstStartStateHolder

import kotlinx.coroutines.flow.Flow

interface FirstStartStateHolder {

    val isFirstStartOfApplication: Flow<Boolean>

    suspend fun markFirstStartOfApplication()
}